import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class MaskPhone : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilter(text)
    }
}


fun maskFilter(text: AnnotatedString): TransformedText {

    // NNNNN-NNN
    val trimmed = if (text.text.length >= 11) text.text.substring(0..10) else text.text
    var out = ""
    for (i in trimmed.indices) {
        if (i==0) out += "("
        out += trimmed[i]
        if (i==1) out += ") "
        //if (i==4) out += "-"
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset == 0) return offset
            if (offset == 1) return offset+1
            if (offset <= 11) return offset+3
            return 14

        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <=14 && offset!=2) return offset -3
            return 11
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}
