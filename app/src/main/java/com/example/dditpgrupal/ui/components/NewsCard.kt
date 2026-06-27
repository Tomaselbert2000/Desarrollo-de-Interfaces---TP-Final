package com.example.dditpgrupal.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dditpgrupal.data.News
import com.example.dditpgrupal.data.dummyNewsList

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Composable
fun NewsCard(
    news: News,
    onReadMore: () -> Unit = {},
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = news.category,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier =
                        Modifier
                            .background(
                                MaterialTheme.colorScheme.primaryContainer,
                                RoundedCornerShape(4.dp),
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${news.date.dayOfMonth}/${news.date.monthValue}/${news.date.year}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = news.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = news.summary,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                TextButton(onClick = onReadMore) {
                    Text(
                        text = "Leer m\u00e1s",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun NewsCardPreview() {
    NewsCard(news = dummyNewsList.first())
}
