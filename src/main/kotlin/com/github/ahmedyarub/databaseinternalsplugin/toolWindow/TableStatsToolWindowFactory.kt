package com.github.ahmedyarub.databaseinternalsplugin.toolWindow

import com.github.ahmedyarub.databaseinternalsplugin.MyBundle
import com.intellij.database.model.ObjectKind
import com.intellij.database.view.DatabaseView
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import java.awt.Container
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JTree


class TableStatsToolWindowFactory : ToolWindowFactory {
    override fun init(toolWindow: ToolWindow) {
        super.init(toolWindow)
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val databaseInternalsToolWindow = DatabaseInternalsToolWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(databaseInternalsToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class DatabaseInternalsToolWindow(toolWindow: ToolWindow) {
        private val tableLabel = JBLabel(MyBundle.message("defaultLabel"))

        private fun getCurrentTableName(): String? {
            var dv = DatabaseView.getDatabaseView(ProjectManager.getInstance().openProjects.first())
            var tableElement = dv.selectedElements.first()?.extractObject()
            if (tableElement?.kind == ObjectKind.TABLE) {
                return tableElement?.name
            }
            return ""
        }

        private fun findJTree(component: JComponent): JTree? {
            if (component is JTree) {
                return component
            }

            for (child in (component as Container).components) {
                if (child is JTree) {
                    return child
                } else if (child is JComponent) {
                    val result = findJTree(child)
                    if (result != null) {
                        return result
                    }
                }
            }

            return null
        }

        private fun createTableSelectionListener() {
            val toolWindowManager: ToolWindowManager =
                ToolWindowManager.getInstance(ProjectManager.getInstance().openProjects.first())
            val contents: Array<Content> = toolWindowManager.getToolWindow("Database")!!.contentManager.contents
            for (content in contents) {
                val component: JComponent = content.component
                findJTree(component)?.addTreeSelectionListener { tableLabel.text = getCurrentTableName() }
            }
        }

        fun getContent() = JBPanel<JBPanel<*>>().apply {
            add(tableLabel)
            createTableSelectionListener()
        }
    }
}
