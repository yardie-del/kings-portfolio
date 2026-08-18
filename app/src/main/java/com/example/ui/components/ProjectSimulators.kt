package com.example.ui.components

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.models.Project
import com.example.data.models.SimulatorType
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectSimulatorDialog(
    project: Project,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.background,
            border = androidx.compose.foundation.BorderStroke(
                1.5.dp,
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            ),
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .fillMaxHeight(0.90f)
                .padding(vertical = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(EmeraldTech))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "LIVE INTERACTIVE DEMO",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = project.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Divider(
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                    modifier = Modifier.padding(vertical = 12.dp)
                )

                // Dispatch to specific simulator
                Box(modifier = Modifier.weight(1f)) {
                    when (project.simulatorType) {
                        SimulatorType.NYUMBA_LINK_PROPTECH -> NyumbaLinkSimulator()
                        SimulatorType.AFRICA_2050_STORYBOARD -> Africa2050Simulator()
                        SimulatorType.AGRITECH_EXCHANGE -> AgriTechSimulator()
                        SimulatorType.CYBERSECURITY_TERMINAL -> CyberSecurityTerminalSimulator()
                        SimulatorType.ARCHCONNECT_PREVIEW -> NyumbaLinkSimulator()
                        else -> {
                            Text(
                                text = "Interactive preview ready for ${project.title}",
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

// ----------------------------------------------------
// 1. NYUMBALINK PROPTECH SIMULATOR
// ----------------------------------------------------
@Composable
private fun NyumbaLinkSimulator() {
    val context = LocalContext.current
    var selectedLocation by remember { mutableStateOf("All Nairobi") }
    var selectedType by remember { mutableStateOf("All Types") }
    var showBookingSuccess by remember { mutableStateOf<String?>(null) }
    var isBooking by remember { mutableStateOf(false) }

    val locations = listOf("All Nairobi", "Kilimani", "Westlands", "Roysambu", "Karen", "South B")
    val propertyTypes = listOf("All Types", "1 Bedroom", "2 Bedroom", "Studio", "Bedsitter")

    data class Listing(
        val id: String,
        val title: String,
        val location: String,
        val type: String,
        val price: String,
        val deposit: String,
        val amenities: String,
        val landlord: String,
        val rating: String
    )

    val listings = remember {
        listOf(
            Listing("1", "Modern Executive 1-Bedroom", "Kilimani", "1 Bedroom", "KES 42,000/mo", "KES 42,000", "High-speed Wi-Fi, Gym, Backup Generator, CCTV", "James Mwangi (Verified Landlord)", "4.9 ★ (18 reviews)"),
            Listing("2", "Spacious 2-Bedroom Master Ensuite", "Westlands", "2 Bedroom", "KES 65,000/mo", "KES 65,000", "Borehole, 2 Balconies, Solar Heating, High Floor", "Sarah Otieno (SuperHost)", "4.8 ★ (24 reviews)"),
            Listing("3", "Chic Studio Apartment w/ Balcony", "Roysambu", "Studio", "KES 18,500/mo", "KES 18,500", "Near Thika Road Mall, 24/7 Water, Rooftop Hangout", "David Kamau (Verified)", "4.7 ★ (12 reviews)"),
            Listing("4", "Luxury Garden Cottage 2-BHK", "Karen", "2 Bedroom", "KES 85,000/mo", "KES 85,000", "Private Lawn, Gated Security, Electric Fence", "Grace Njeri (Verified)", "5.0 ★ (9 reviews)"),
            Listing("5", "Convenient Budget Bedsitter", "South B", "Bedsitter", "KES 12,000/mo", "KES 12,000", "Near CBD & Express Way, Token Meter", "Peter Ochieng (Verified)", "4.6 ★ (15 reviews)")
        )
    }

    val filteredListings = listings.filter {
        (selectedLocation == "All Nairobi" || it.location == selectedLocation) &&
                (selectedType == "All Types" || it.type == selectedType)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "NyumbaLink Rental Marketplace Prototype",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Explore verified Nairobi rental homes with simulated instant M-Pesa viewing reservations.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Location Filter Chips
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(locations) { loc ->
                FilterChip(
                    selected = selectedLocation == loc,
                    onClick = { selectedLocation = loc },
                    label = { Text(loc, fontSize = 11.sp) },
                    shape = RoundedCornerShape(8.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = Color(0xFF00363D)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Listings List
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredListings) { item ->
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = item.price,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 13.sp,
                                color = CyanAccent
                            )
                        }

                        Text(
                            text = "📍 ${item.location} • ${item.type} • ${item.rating}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )

                        Text(
                            text = "✨ Amenities: ${item.amenities}",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "👤 ${item.landlord}",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.primary
                            )

                            Button(
                                onClick = {
                                    showBookingSuccess = "Viewing scheduled for ${item.title}! M-Pesa STK prompt KES 100 escrow reserved."
                                    Toast.makeText(context, "M-Pesa STK Push Sent: Enter PIN to confirm viewing slot", Toast.LENGTH_SHORT).show()
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text("Book Viewing", fontSize = 12.sp, color = Color(0xFF00363D), fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        if (showBookingSuccess != null) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = EmeraldTech.copy(alpha = 0.15f),
                border = androidx.compose.foundation.BorderStroke(1.dp, EmeraldTech),
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldTech, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = showBookingSuccess!!, fontSize = 12.sp, color = EmeraldTech, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

// ----------------------------------------------------
// 2. AFRICA 2050 STORYBOARD EXPLORER
// ----------------------------------------------------
@Composable
private fun Africa2050Simulator() {
    val episodes = listOf(
        Pair("Nairobi 2050", "Silicon Savannah transformed into a high-density vertical solar metropolis with hyperloop maglev transit connecting Konza Technopolis to CBD in 7 minutes."),
        Pair("Mombasa 2050", "Autonomous maritime cargo ports powered by wave kinetic generators and coral reef bio-restoration reefs."),
        Pair("Kenyan Schools in 2050", "Classrooms replaced by interactive neural holographic immersion pods where children learn quantum computing and Swahili-English AI linguistics."),
        Pair("Future of Farming in Kenya", "Vertical automated aeroponic skyscrapers in Rift Valley producing 10x organic yield with 95% water reduction."),
        Pair("AI-Powered African Cities", "Decentralized solar microgrids trading surplus kilowatt-hours peer-to-peer via pan-African cryptographic ledgers."),
        Pair("A Kenyan Programmer in 2050", "Architecting decentralized planetary AI agents from a solar beach house in Diani.")
    )

    var selectedIndex by remember { mutableStateOf(0) }
    val current = episodes[selectedIndex]

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Africa 2050 Cinematic Speculative Engine",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Select an episode to explore the futuristic tech scenario, architecture, and prompt matrix.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Episode selector tabs
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(episodes.indices.toList()) { idx ->
                FilterChip(
                    selected = selectedIndex == idx,
                    onClick = { selectedIndex = idx },
                    label = { Text(episodes[idx].first, fontSize = 11.sp) },
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            borderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Movie, contentDescription = null, tint = CyanAccent, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "EPISODE ${selectedIndex + 1}: ${current.first.uppercase()}",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 13.sp,
                    color = CyanAccent,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = current.second,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // AI Prompt Matrix Inspector
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF040812),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x3300E5FF)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "GENERATIVE PROMPT MATRIX",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = CyanAccent
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "hyper-detailed African solar architecture, cinematic 8k, neon cyan and gold illuminations, ${current.first.lowercase()} skyline, indigenous geometric fractals, ultra-modern transit, photorealistic unreal engine 5 render --ar 16:9",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = TextSecondaryDark,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}

// ----------------------------------------------------
// 3. AGRITECH FARMER-TO-MARKET SIMULATOR
// ----------------------------------------------------
@Composable
private fun AgriTechSimulator() {
    var selectedCrop by remember { mutableStateOf("Hass Avocados") }
    var quantityKg by remember { mutableStateOf("500") }
    var isTradeActive by remember { mutableStateOf(false) }

    val crops = listOf(
        Triple("Hass Avocados", "KES 85 / kg", "Murang'a / Nyeri"),
        Triple("Dry Maize", "KES 42 / kg", "Eldoret / Kitale"),
        Triple("Fresh Tomatoes", "KES 60 / kg", "Kirinyaga"),
        Triple("French Beans", "KES 110 / kg", "Machakos"),
        Triple("Coffee Grade AA", "KES 340 / kg", "Kiambu")
    )

    val currentCrop = crops.firstOrNull { it.first == selectedCrop } ?: crops.first()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Smart AgriTech Direct Farmer-to-Buyer Exchange",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Simulate real-time Kenyan wholesale commodity pricing and buyer contract matching.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Crop selector
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(crops) { (name, _, _) ->
                FilterChip(
                    selected = selectedCrop == name,
                    onClick = {
                        selectedCrop = name
                        isTradeActive = false
                    },
                    label = { Text(name, fontSize = 11.sp) },
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        GlassCard(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = currentCrop.first, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = "Origin Hub: ${currentCrop.third}", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Text(text = currentCrop.second, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = EmeraldTech)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // AI Farm telemetry box
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = "🌱 AI Crop Diagnostics & Forecast", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Optimal harvest window: Next 5 days. Weather: Scattered showers (82% soil moisture). Estimated batch valuation: KES ${(quantityKg.toIntOrNull() ?: 500) * (currentCrop.second.filter { it.isDigit() }.toIntOrNull() ?: 50)}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = { isTradeActive = true },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = EmeraldTech),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Default.Handshake, contentDescription = null, tint = Color.Black, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Match with Verified Buyer & Lock Contract", color = Color.Black, fontWeight = FontWeight.Bold)
            }

            if (isTradeActive) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = EmeraldTech.copy(alpha = 0.15f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, EmeraldTech),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "✅ Buyer Matched: Twiga Foods Nairobi Hub accepted lot for ${currentCrop.first}. M-Pesa Escrow contract initialized.",
                        fontSize = 12.sp,
                        color = EmeraldTech,
                        modifier = Modifier.padding(10.dp),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

// ----------------------------------------------------
// 4. CYBERSECURITY TERMINAL SIMULATOR
// ----------------------------------------------------
@Composable
private fun CyberSecurityTerminalSimulator() {
    var commandInput by remember { mutableStateOf("") }
    val terminalLogs = remember {
        mutableStateListOf(
            "Moses Ominde Security Sandbox v2.4 (Kenya Node)",
            "Type 'help' to see available defense and scanning commands.",
            "moses@cyber-lab:~$ nmap --target demo-target.ke"
        )
    }

    fun executeCommand(cmd: String) {
        val trimmed = cmd.trim()
        if (trimmed.isEmpty()) return

        terminalLogs.add("moses@cyber-lab:~$ $trimmed")

        when {
            trimmed.equals("help", true) -> {
                terminalLogs.add("Available commands:")
                terminalLogs.add("  scan        - Run vulnerability port reconnaissance")
                terminalLogs.add("  status      - Display firewall & defensive posture")
                terminalLogs.add("  defense     - Enable automated rate-limiting & IP ban")
                terminalLogs.add("  audit       - Generate OWASP Top 10 compliance score")
                terminalLogs.add("  whoami      - Display security engineer identity")
                terminalLogs.add("  clear       - Reset terminal buffer")
            }
            trimmed.startsWith("scan", true) || trimmed.contains("nmap", true) -> {
                terminalLogs.add("[+] Scanning target: 192.168.1.104 [Ports 1-1024]")
                terminalLogs.add("[+] Port 22/tcp  OPEN  (OpenSSH 8.9p1 Ubuntu)")
                terminalLogs.add("[+] Port 80/tcp  OPEN  (Nginx 1.18.0 - TLS 1.3 Active)")
                terminalLogs.add("[+] Port 5432/tcp FILTERED (PostgreSQL - Localhost Bound)")
                terminalLogs.add("[✔] Vulnerability Scan complete: 0 Critical, 0 High.")
            }
            trimmed.equals("status", true) -> {
                terminalLogs.add("[+] Uptime: 99.98% | Active Nodes: 4 | Intrusion Defense: ACTIVE")
                terminalLogs.add("[+] SSH Key Auth Only | Root Login: DISABLED")
            }
            trimmed.equals("defense", true) -> {
                terminalLogs.add("[+] Activating iptables honeypot & fail2ban filters...")
                terminalLogs.add("[✔] 14 Malicious bot scans automatically mitigated.")
            }
            trimmed.equals("audit", true) -> {
                terminalLogs.add("[+] OWASP Top 10 Audit Result: 98/100 (A+ Grade)")
                terminalLogs.add("[+] SQLi: Protected (Django ORM parameterized queries)")
                terminalLogs.add("[+] XSS / CSRF: Protected (Token enforced)")
            }
            trimmed.equals("whoami", true) -> {
                terminalLogs.add("Moses Ominde | BSc IT Software Developer | Ethical Security Engineer")
            }
            trimmed.equals("clear", true) -> {
                terminalLogs.clear()
                terminalLogs.add("Moses Ominde Security Sandbox v2.4 (Kenya Node)")
                terminalLogs.add("Type 'help' to see available commands.")
            }
            else -> {
                terminalLogs.add("bash: $trimmed: command not found. Type 'help' for valid commands.")
            }
        }
        commandInput = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF040810))
            .border(1.dp, CyanAccent.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
            .padding(10.dp)
    ) {
        // Terminal Title Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(RoseTech))
                Spacer(modifier = Modifier.width(6.dp))
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(AmberTech))
                Spacer(modifier = Modifier.width(6.dp))
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(EmeraldTech))
            }
            Text(
                text = "kali@ominde-lab: ~/defense",
                fontFamily = FontFamily.Monospace,
                fontSize = 11.sp,
                color = TextSecondaryDark
            )
        }

        Divider(color = Color(0x3300E5FF), modifier = Modifier.padding(vertical = 8.dp))

        // Terminal Output Screen
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(terminalLogs) { line ->
                Text(
                    text = line,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    color = when {
                        line.startsWith("moses@") || line.startsWith("kali@") -> CyanAccent
                        line.startsWith("[✔]") || line.contains("Protected") || line.contains("complete") -> EmeraldTech
                        line.startsWith("[+]") -> BlueElectric
                        line.contains("Available") || line.contains("Type 'help'") -> AmberTech
                        line.contains("not found") -> RoseTech
                        else -> TextPrimaryDark
                    }
                )
            }
        }

        // Quick command chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            listOf("help", "scan", "status", "audit", "defense").forEach { cmd ->
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFF131F37),
                    border = androidx.compose.foundation.BorderStroke(0.5.dp, CyanAccent.copy(alpha = 0.3f)),
                    modifier = Modifier.clickable { executeCommand(cmd) }
                ) {
                    Text(
                        text = cmd,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 10.sp,
                        color = CyanAccent,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }
        }

        // Input Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "$ ",
                fontFamily = FontFamily.Monospace,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = CyanAccent
            )
            TextField(
                value = commandInput,
                onValueChange = { commandInput = it },
                placeholder = { Text("type command...", fontSize = 12.sp, color = TextMutedDark, fontFamily = FontFamily.Monospace) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = androidx.compose.ui.text.TextStyle(fontFamily = FontFamily.Monospace, fontSize = 12.sp),
                modifier = Modifier
                    .weight(1f)
                    .testTag("terminal_input_field")
            )
            IconButton(
                onClick = { executeCommand(commandInput) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Run", tint = CyanAccent, modifier = Modifier.size(16.dp))
            }
        }
    }
}
