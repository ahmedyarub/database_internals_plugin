package com.github.ahmedyarub.databaseinternalsplugin.toolWindow

import com.github.ahmedyarub.databaseinternalsplugin.MyBundle
import com.github.ahmedyarub.databaseinternalsplugin.services.MyProjectService
import com.intellij.database.model.ObjectKind
import com.intellij.database.view.DatabaseView
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import javax.swing.JButton


class MyToolWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val databaseInternalsToolWindow = DatabaseInternalsToolWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(databaseInternalsToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class DatabaseInternalsToolWindow(toolWindow: ToolWindow) {

        private val service = toolWindow.project.service<MyProjectService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {
            val label = JBLabel(MyBundle.message("defaultLabel"))

            add(label)
            add(JButton(MyBundle.message("analyzeTable")).apply {
                addActionListener {
                    var dv = DatabaseView.getDatabaseView(ProjectManager.getInstance().openProjects.first())
                    var tableElement = dv.selectedElements.first()?.extractObject()
                    if (tableElement?.kind == ObjectKind.TABLE) {
                        label.text = tableElement?.name
                    }
                }
            })
        }
    }
}
