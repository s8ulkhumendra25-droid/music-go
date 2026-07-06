package com.musicgo.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.musicgo.app.ui.theme.OnSurfaceVariant
import com.musicgo.app.ui.theme.SearchBarBackground
import com.musicgo.app.ui.theme.TextSecondary
import com.musicgo.app.ui.theme.AppTypography

@Composable
fun MusicGoSearchBar(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    onSettingsClick: () -> Unit = {},
    placeholder: String = "Search"
) {
    var textValue by remember { mutableStateOf(value) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = SearchBarBackground,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = OnSurfaceVariant,
            modifier = Modifier.padding(end = 8.dp)
        )

        BasicTextField(
            value = textValue,
            onValueChange = {
                textValue = it
                onValueChange(it)
            },
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 12.dp),
            textStyle = AppTypography.bodyMedium.copy(color = TextSecondary),
            decorationBox = { innerTextField ->
                if (textValue.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = AppTypography.bodyMedium,
                        color = OnSurfaceVariant
                    )
                }
                innerTextField()
            }
        )

        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier.padding(end = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = OnSurfaceVariant
            )
        }
    }
}
