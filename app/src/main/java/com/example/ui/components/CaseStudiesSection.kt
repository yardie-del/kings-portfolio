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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioRepository
import com.example.data.models.Project
import com.example.ui.theme.*

@Composable
fun CaseStudiesSection(
    onOpenCaseStudy: (Project) -> Unit,
    onOpenSimulator: (Project) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val caseStudyProjects = PortfolioRepository.projects.filter { it.architecture.isNotBlank() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        SectionHeader(
            badgeText = "Deep Dives",
            title = "Engineering Case Studies",
            subtitle = "Detailed breakdowns of problem formulation, architectural design, trade-offs, testing, and production outcomes."
        )

        Spacer(modifier = Modifier.height(18.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            caseStudyProjects.forEachIndexed { index, project ->
                CaseStudyCard(
                    number = String.format("%02d", index + 1),
                    project = project,
                    onOpenCaseStudy = { onOpenCaseStudy(project) },
                    onOpenSimulator = { onOpenSimulator(project) },
                    onOpenGithub = { openExternalUrl(context, project.githubUrl) },
                    onOpenLiveDemo = { project.liveDemoUrl?.let { openExternalUrl(context, it) } },
                    onDownloadApk = { project.apkUrl?.let { openExternalUrl(context, it) } }
                )
            }
        }
    }
}

@Composable
private fun CaseStudyCard(
    number: String,
    project: Project,
    onOpenCaseStudy: () -> Unit,
    onOpenSimulator: () -> Unit,
    onOpenGithub: () -> Unit,
    onOpenLiveDemo: () -> Unit,
    onDownloadApk: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(22.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(
            1.2.dp,
            if (project.featured) MaterialTheme.colorScheme.primary.copy(alpha = 0.45f)
            else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
        ),
        tonalElevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .testTag("case_study_card_${project.slug}")
    ) {
        Column {
            // Hero Image or Banner with Number Index
            if (project.imageResId != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                ) {
                    Image(
                        painter = painterResource(id = project.imageResId),
                        contentDescription = project.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0x66070B14),
                                        MaterialTheme.colorScheme.surface.copy(alpha = 0.96f)
                                    )
                                )
                            )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primary,
                            contentColor = Color(0xFF00363D)
                        ) {
                            Text(
                                text = "CASE STUDY $number",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xCC070B14),
                            border = androidx.compose.foundation.BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline)
                        ) {
                            Text(
                                text = project.status,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = CyanAccent,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = "CASE STUDY $number",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                    ) {
                        Text(
                            text = project.status,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            // Body
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = project.tagline,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 2.dp, bottom = 10.dp)
                )

                // The Problem & Solution Snippet
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                    border = androidx.compose.foundation.BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(verticalAlignment = Alignment.Top) {
                            Icon(imageVector = Icons.Default.WarningAmber, contentDescription = null, tint = RoseTech, modifier = Modifier.size(16.dp).padding(top = 2.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "PROBLEM FORMULATION", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = RoseTech, letterSpacing = 0.8.sp)
                                Text(text = project.problemSolved, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 18.sp)
                            }
                        }
                        Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                        Row(verticalAlignment = Alignment.Top) {
                            Icon(imageVector = Icons.Default.CheckCircleOutline, contentDescription = null, tint = EmeraldTech, modifier = Modifier.size(16.dp).padding(top = 2.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "ENGINEERED ARCHITECTURE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldTech, letterSpacing = 0.8.sp)
                                Text(text = project.solution, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 18.sp)
                            }
                        }
                    }
                }

                // Tech tags
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    project.technologies.take(4).forEach { tech ->
                        TechTag(text = tech)
                    }
                    if (project.technologies.size > 4) {
                        TechTag(text = "+${project.technologies.size - 4}")
                    }
                }

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onOpenCaseStudy,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color(0xFF00363D)
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp),
                        modifier = Modifier.weight(1f).heightIn(min = 44.dp).testTag("explore_case_study_${project.slug}")
                    ) {
                        Icon(imageVector = Icons.Default.MenuBook, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Read Full Case Study", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }

                    if (project.simulatorType != null) {
                        FilledTonalButton(
                            onClick = onOpenSimulator,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = BlueElectric.copy(alpha = 0.18f),
                                contentColor = BlueElectric
                            ),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp),
                            modifier = Modifier.heightIn(min = 44.dp).testTag("case_study_sim_${project.slug}")
                        ) {
                            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Demo", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }

                    if (project.apkUrl != null) {
                        IconButton(
                            onClick = onDownloadApk,
                            modifier = Modifier
                                .size(44.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                                .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                        ) {
                            Icon(imageVector = Icons.Default.Download, contentDescription = "Download APK", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                        }
                    }

                    IconButton(
                        onClick = onOpenGithub,
                        modifier = Modifier
                            .size(44.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                    ) {
                        Icon(imageVector = Icons.Default.Code, contentDescription = "GitHub Repo", tint = MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}
