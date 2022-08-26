package me.iso88591.cacu

import androidx.lifecycle.ViewModel
import me.iso88591.cacu.logics.KeyBoardInput
import me.iso88591.cacu.logics.KeyBoardUiInput

class CacuVm:ViewModel() {

    val keyBoardInput = KeyBoardInput()

    val input = KeyBoardUiInput(false,keyBoardInput) {
    }

}