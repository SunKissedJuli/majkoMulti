package com.example.majkomulti.screen.taskEditor

import androidx.compose.ui.graphics.Color
import com.example.majkomulti.domain.modelsUI.InfoUi
import com.example.majkomulti.domain.modelsUI.Note.NoteDataUi
import com.example.majkomulti.domain.modelsUI.Project.ProjectDataUi
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi
import com.example.majkomulti.domain.modelsUI.UploadFilesUi

data class TaskEditorState(
    val taskText: String = DEFAULT_STRING,
    val taskName: String = DEFAULT_STRING,
    val taskDeadline: String = DEFAULT_STRING,
    val taskPriority: Int = DEFAULT_INT,
    val taskStatus: Int = DEFAULT_INT,
    val taskPriorityName: String = DEFAULT_STRING,
    val taskStatusName: String = DEFAULT_STRING,
    val taskProject: String = DEFAULT_STRING,
    val taskProjectObj: ProjectDataUi = ProjectDataUi.empty(),
    val taskId: String = DEFAULT_TASK_ID,
    val taskFiles: List<UploadFilesUi> = emptyList(),
    val backgroundColor: Color = DEFAULT_BACKGROUND_COLOR,
    val noteText: String = DEFAULT_STRING,
    val newNote: Boolean = DEFAULT_BOOLEAN,
    val notes: List<NoteDataUi>? = listOf(),
    val subtask: List<TaskDataUi>? = listOf(),
    val isAdding: Boolean = DEFAULT_BOOLEAN,
    val subtaskText: String = DEFAULT_STRING,
    val subtaskName: String = DEFAULT_STRING,
    val subtaskDeadline: String = DEFAULT_STRING,
    val subtaskPriority: Int = DEFAULT_INT,
    val subtaskStatus: Int = DEFAULT_INT,
    val subtaskProject: String = DEFAULT_STRING,
    val statuses: List<InfoUi> = listOf(),
    val proprieties: List<InfoUi> = listOf(),
    val exitDialog: Boolean = DEFAULT_BOOLEAN,
    val expanded: Boolean = DEFAULT_BOOLEAN
) {
    companion object {
        const val DEFAULT_STRING = ""
        const val DEFAULT_INT = 1
        const val DEFAULT_TASK_ID = "0"
        val DEFAULT_BACKGROUND_COLOR = Color.White
        const val DEFAULT_BOOLEAN = false

        fun InitState() = TaskEditorState()
    }
}
