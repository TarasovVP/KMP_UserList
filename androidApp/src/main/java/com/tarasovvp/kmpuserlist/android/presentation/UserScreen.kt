package com.tarasovvp.kmpuserlist.android.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.tarasovvp.kmpuserlist.User

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun UsersScreen(
    users: List<User>,
    isRefreshing: Boolean,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit,
    onEmailClick: (String) -> Unit = {},
    onPhoneClick: (String) -> Unit = {}
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(users, key = { it.email }) { user ->
                UserCard(user, onEmailClick = onEmailClick, onPhoneClick = onPhoneClick)
            }
        }
    }
}

@Composable
private fun UserCard(
    user: User,
    modifier: Modifier = Modifier,
    onEmailClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserAvatar(url = user.image, contentDesc = "${user.firstName} ${user.lastName}")

            Spacer(Modifier.width(14.dp))

            Column(Modifier.weight(1f)) {
                Text(
                    text = "${user.firstName} ${user.lastName}",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(6.dp))

                Detail(label = "Birth:", value = "${user.birthDate} (${user.age} years old)")
                GenderChip(gender = user.gender)

                Spacer(Modifier.height(6.dp))

                ContactDetail(
                    label = "Email:",
                    value = user.email,
                    onClick = { onEmailClick(user.email) }
                )
                ContactDetail(
                    label = "Phone:",
                    value = user.phone,
                    onClick = { onPhoneClick(user.phone) }
                )
            }
        }
    }
}

@Composable
private fun UserAvatar(url: String, contentDesc: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = contentDesc,
        modifier = modifier
            .size(72.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant),
    )
}

@Composable
private fun Detail(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = label, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.width(8.dp))
        Text(text = value)
    }
}

@Composable
private fun ContactDetail(label: String, value: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(text = label, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.width(8.dp))
        Text(
            text = value,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun GenderChip(gender: String) {
    val color = when (gender.lowercase()) {
        "male" -> MaterialTheme.colorScheme.primary
        "female" -> MaterialTheme.colorScheme.tertiary
        else -> MaterialTheme.colorScheme.secondary
    }
    AssistChip(
        onClick = {},
        label = { Text(text = gender) },
        colors = AssistChipDefaults.assistChipColors(
            labelColor = color
        ),
        modifier = Modifier.padding(top = 2.dp)
    )
}