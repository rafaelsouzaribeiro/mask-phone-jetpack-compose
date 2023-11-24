# mask-phone-jetpack-compose
how to use:

val visualTransformationPhone: VisualTransformation = remember { MaskPhone() }

TextField(
          value = state.text,
          onValueChange = { if (it.length <= 11 && it.isDigitsOnly()) state.updateText(it)  },
          placeholder = { Text(text = placeholderText) },
          leadingIcon = { Icon(imageVector = leadingIconVector, contentDescription = null) },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
          modifier = modifier,
          visualTransformation = visualTransformationPhone
      )
