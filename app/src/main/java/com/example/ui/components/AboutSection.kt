package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.PortfolioRepository
import com.example.ui.theme.*

@Composable
fun AboutSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        SectionHeader(
            badgeText = "About Me",
            title = "Engineering Practical Tech for Africa & Beyond",
            subtitle = "Turning complex local and global challenges into reliable, high-performance software products."
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Main Narrative Card
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            borderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.profile_photo),
                    contentDescription = "Moses Ominde Profile Photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Text(
                        text = "Moses Ominde",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "3rd-Year BSc Information Technology Student & Software Engineer",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "I am a software developer and 3rd-year BSc Information Technology student based in Kenya. I build robust digital systems, intuitive web and mobile interfaces, secure backend architectures, and intelligent AI-driven applications.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "My philosophy centers on craftsmanship: taking authentic, everyday frictions—from house-hunting challenges in Kenyan cities to food supply chain inefficiencies—and engineering scalable, production-grade solutions that deliver measurable value.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 24.sp
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Key Focus Areas Grid
        Text(
            text = "AREAS OF FOCUS & INTEREST",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = 1.2.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        val interestAreas = listOf(
            InterestItem("Software Engineering", Icons.Default.Code, "System architecture, clean code, scalable patterns"),
            InterestItem("Web Development", Icons.Default.Language, "Responsive SPAs, modern React & Tailwind UI"),
            InterestItem("Mobile Development", Icons.Default.PhoneAndroid, "Cross-platform Flutter & native Compose apps"),
            InterestItem("Artificial Intelligence & ML", Icons.Default.Psychology, "Generative models, LLM workflows & automation"),
            InterestItem("Cybersecurity", Icons.Default.Security, "Ethical testing, network analysis & Linux hardening"),
            InterestItem("Data Analysis", Icons.Default.Analytics, "Data pipelines, market price models & insights"),
            InterestItem("Cloud Technologies", Icons.Default.Cloud, "Docker containerization, REST APIs & databases"),
            InterestItem("Startup Building", Icons.Default.Lightbulb, "0 to 1 MVP execution & commercial tech products")
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            interestAreas.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    rowItems.forEach { item ->
                        FocusAreaCard(
                            item = item,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Currently Learning Section
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            borderColor = IndigoAccent.copy(alpha = 0.35f)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AutoStories,
                    contentDescription = null,
                    tint = IndigoAccent,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Currently Deepening Knowledge In",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            val currentlyLearning = listOf(
                "Kotlin Multiplatform (KMP) & Compose Multiplatform",
                "Advanced Linux System Auditing & Network Forensics",
                "AI/LLM Integration Patterns in Mobile & Web Architectures",
                "Distributed Event-Driven Microservices & Cloud Scaling"
            )

            currentlyLearning.forEach { learningItem ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(IndigoAccent)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = learningItem,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

private data class InterestItem(
    val title: String,
    val icon: ImageVector,
    val subtitle: String
)

@Composable
private fun FocusAreaCard(
    item: InterestItem,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        border = androidx.compose.foundation.BorderStroke(
            0.8.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
        ),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.subtitle,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 15.sp
            )
        }
    }
}
