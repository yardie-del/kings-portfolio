package com.example.data

import com.example.R
import com.example.data.models.*

object PortfolioRepository {

    val projects: List<Project> = listOf(
        Project(
            id = "kings-portfolio",
            title = "Kings Portfolio",
            slug = "kings-portfolio",
            category = ProjectCategory.MOBILE_APPS,
            tagline = "Premium Native Android Developer Portfolio & Client Acquisition Platform",
            description = "A native Android portfolio application engineered with modern Kotlin and Jetpack Compose, designed to showcase verified software engineering projects, live GitHub activity, cybersecurity labs, and direct client acquisition channels.",
            problemSolved = "Eliminates static, non-interactive PDF resumes and demonstrates verifiable software engineering competence through live Android runtime execution, responsive UI systems, Room database caching, and real-time GitHub REST API integration.",
            solution = "A high-performance Material 3 application featuring modular MVVM architecture, type-safe navigation, interactive project simulators, dynamic theme switching, local contact inquiry persistence, and live developer telemetry.",
            technologies = listOf(
                "Kotlin", "Jetpack Compose", "Material 3", "Room Database",
                "Coroutines & Flow", "Retrofit", "GitHub API", "Clean MVVM"
            ),
            imageResId = R.drawable.profile_photo,
            status = "Completed / Production",
            featured = true,
            githubUrl = "https://github.com/yardie-del/kings-portfolio",
            liveDemoUrl = "https://github.com/yardie-del/kings-portfolio",
            apkUrl = "https://github.com/yardie-del/kings-portfolio/releases",
            date = "2025 - 2026",
            keyFeatures = listOf(
                "Declarative Jetpack Compose UI with adaptive Window Size Classes",
                "Live GitHub REST API integration with real-time repo & commit telemetry",
                "Local Room Database for offline project inquiry management",
                "Interactive Project Simulators & Case Study deep-dive dialogs",
                "Cybersecurity Interactive Lab Terminal with practical defense simulations",
                "Client-acquisition 'Build With Me' project request pipeline",
                "Built-in ATS-friendly CV Viewer and Resume Export"
            ),
            architecture = "Clean MVVM (Model-View-ViewModel) architecture with unidirectional data flow. Repository pattern abstracts local Room persistence and remote Retrofit HTTP calls. Reactive UI state driven by Kotlin StateFlow and Jetpack Compose.",
            researchIdea = "Researched modern Android architectural standards and employer acquisition requirements to build a portfolio that acts as proof-of-work rather than simple text claims.",
            challenges = listOf(
                ChallengeSolution(
                    challenge = "Gracefully handling GitHub API rate limits without disrupting user browsing.",
                    solution = "Implemented fallback offline data structures with intelligent HTTP caching and user-configurable custom username lookups."
                ),
                ChallengeSolution(
                    challenge = "Ensuring smooth 60fps scrolling performance with complex nested cards and dynamic gradients.",
                    solution = "Optimized Compose layout passes, replaced heavy canvas operations with scoped drawBehind modifiers, and utilized LazyColumn key indexing."
                )
            ),
            results = "Shipped a native Android APK with zero jank, sub-100ms cold start, and full Material 3 design compliance.",
            lessonsLearned = "Mastered Jetpack Compose state hoisting, custom canvas drawing, Room database migrations, and type-safe Kotlin coroutine flows.",
            metrics = listOf(
                "Architecture" to "Clean MVVM",
                "UI Toolkit" to "Jetpack Compose",
                "Local Database" to "Room SQLite",
                "Performance" to "60 FPS Native"
            ),
            futureRoadmap = listOf(
                "Biometric authentication option for private inquiry inbox",
                "Push notifications for client inquiry follow-ups",
                "Export portfolio to interactive web dashboard via Kotlin Multiplatform"
            ),
            simulatorType = null
        ),
        Project(
            id = "nyumbalink",
            title = "NyumbaLink",
            slug = "nyumbalink",
            category = ProjectCategory.STARTUP_PROJECTS,
            tagline = "The Next-Gen Kenyan House-Hunting & PropTech Rental Marketplace",
            description = "A Kenyan house-hunting and rental marketplace designed to make finding homes faster, safer, and easier with verified landlords, interactive maps, and automated viewings.",
            problemSolved = "Eliminates predatory middleman broker fees, rental scams, and wasted physical viewing trips by providing verified landlord listings, direct chat, and geolocation matching in Kenyan cities.",
            solution = "A high-performance PropTech platform featuring GPS polygon search, escrow viewing deposits, automated landlord management dashboards, M-Pesa payment gateways, and moving logistics.",
            technologies = listOf(
                "React", "Tailwind CSS", "Django", "Django REST Framework",
                "PostgreSQL", "Redis", "Celery", "Docker", "Maps API", "M-Pesa Daraja"
            ),
            imageResId = R.drawable.img_nyumbalink,
            status = "Flagship MVP / Live Beta",
            featured = true,
            githubUrl = "https://github.com/yardie-del/nyumbalink-backend",
            liveDemoUrl = "https://nyumbalink.co.ke",
            apkUrl = null,
            date = "2024 - 2026",
            keyFeatures = listOf(
                "Verified Property Listings with High-Res Media & Floor Plans",
                "Advanced Geolocation Search & Neighborhood Radius Filters",
                "Interactive Maps with Transit, Schools & Security Indicators",
                "Dual Tenant & Landlord Account Ecosystems",
                "Instant Viewing Schedule & Escrow Booking System",
                "Real-Time Landlord-Tenant Messaging & In-App Inquiries",
                "Automated Rent Invoicing & Kenyan M-Pesa Payment Integration",
                "Tenant Rental History & Verified Landlord Reputation Ratings",
                "Integrated Move-In & Relocation Logistics Assistant"
            ),
            architecture = "Decoupled architecture: React Single-Page Application on the frontend communicating via RESTful APIs with a Django REST Framework backend. PostgreSQL with spatial extensions manages geodata. Celery with Redis broker handles background tasks such as email/SMS alerts, M-Pesa callback processing, and search index caching. Containerized with Docker for rapid cloud deployment.",
            researchIdea = "Conducted field interviews with urban Kenyan renters in Nairobi and Eldoret who reported losing thousands of shillings to unregulated housing brokers.",
            challenges = listOf(
                ChallengeSolution(
                    challenge = "Handling high-concurrency search filtering over thousands of geo-coordinates without latency spikes.",
                    solution = "Implemented Redis spatial caching and indexed bounding-box spatial queries in PostgreSQL, reducing query latency from 320ms to under 28ms."
                ),
                ChallengeSolution(
                    challenge = "Preventing fraudulent fake listings and identity impersonation common in Kenyan real estate.",
                    solution = "Designed a multi-step verification protocol requiring title/utility verification and PIN-based phone validation before listing publication."
                ),
                ChallengeSolution(
                    challenge = "Asynchronous M-Pesa STK Push payment timeouts and network drops.",
                    solution = "Implemented Celery webhook handlers with exponential backoff retry queues and idempotent transaction state machine."
                )
            ),
            results = "Built an end-to-end working beta connecting verified properties with sub-30ms search response times and successful M-Pesa STK push integration.",
            lessonsLearned = "Deepened understanding of distributed transaction management, spatial database indexing with PostGIS, and security validation for financial transactions.",
            metrics = listOf(
                "Search Latency" to "< 30ms",
                "Target Cities" to "Nairobi, Mombasa, Kisumu, Nakuru",
                "M-Pesa Success" to "99.8%",
                "Architecture" to "React + Django REST"
            ),
            futureRoadmap = listOf(
                "AI-powered rental price valuation engine based on localized neighborhood trends",
                "Native mobile application release for Android & iOS using Flutter",
                "Virtual 3D tour rendering for premium residential complexes",
                "Automated utility billing (water & electricity token management)"
            ),
            simulatorType = SimulatorType.NYUMBA_LINK_PROPTECH
        ),
        Project(
            id = "archconnect-ke",
            title = "ArchConnect KE",
            slug = "archconnect-ke",
            category = ProjectCategory.STARTUP_PROJECTS,
            tagline = "Kenyan Architecture Marketplace & Professional Project Collaboration Hub",
            description = "A dedicated Kenyan marketplace connecting property developers, clients, and institutions with licensed architectural firms, registered architects, landscape designers, and architecture students.",
            problemSolved = "Solves the difficulty Kenyan property developers face in discovering verified, licensed architects (BORAQS compliant), while bridging the gap for young architects and students seeking legitimate freelance design contracts and mentorship.",
            solution = "A curated digital platform featuring verified architect credentials, high-resolution CAD/BIM project galleries, Request for Proposal (RFP) bidding, milestone-based escrow contracts, and building code compliance references.",
            technologies = listOf(
                "React", "Tailwind CSS", "JavaScript", "Python",
                "Django REST Framework", "PostgreSQL", "AWS S3 Storage", "REST APIs"
            ),
            imageResId = null,
            status = "In Development / MVP",
            featured = true,
            githubUrl = "https://github.com/yardie-del/archconnect-ke",
            liveDemoUrl = "https://archconnect.co.ke",
            apkUrl = null,
            date = "2025",
            keyFeatures = listOf(
                "Verified Professional Directory (Registered Architects, Landscape Architects, Interior Designers)",
                "Interactive Architectural Portfolio Galleries with High-Res CAD & 3D Renders",
                "Client Project RFP (Request for Proposal) Posting & Bidding Engine",
                "Milestone-based Escrow Payments for Design Phases (Concept, Schematics, Approvals)",
                "Kenyan County Building Approval Checklist & BORAQS Code Reference Guide",
                "Student Showcase & Mentorship Matchmaking Hub"
            ),
            architecture = "React and Tailwind frontend consuming a Django REST backend. Cloud-hosted media storage on Amazon S3 with image optimization pipelines for high-resolution blueprints and rendering files. PostgreSQL database tracking verification statuses, portfolios, and bids.",
            researchIdea = "Surveyed Kenyan construction professionals and university architecture students regarding how freelance design jobs are acquired and verified.",
            challenges = listOf(
                ChallengeSolution(
                    challenge = "Managing high-resolution architectural 3D render files without degrading web page performance.",
                    solution = "Implemented automated thumbnail generation, progressive image loading, and responsive image srcset delivery."
                ),
                ChallengeSolution(
                    challenge = "Verifying professional registration credentials accurately.",
                    solution = "Integrated structured license verification input fields against Kenyan professional board registry formats."
                )
            ),
            results = "Developed the core MVP marketplace architecture with project bidding flows, user profile portfolios, and secure REST endpoints.",
            lessonsLearned = "Gained practical experience designing complex multi-role marketplace workflows (Clients vs Firms vs Students) and media-heavy data pipelines.",
            metrics = listOf(
                "Target Professions" to "Architects, Firms, Students",
                "Security" to "JWT & Role-Based Access",
                "Storage" to "S3 Optimized CDN",
                "Status" to "Active Development"
            ),
            futureRoadmap = listOf(
                "In-browser 3D model viewer for IFC and OBJ architectural files",
                "Direct county planning e-permit tracking integration",
                "Quantity Surveyor and Structural Engineer collaborative workspace"
            ),
            simulatorType = SimulatorType.ARCHCONNECT_PREVIEW
        ),
        Project(
            id = "agritech-platform",
            title = "Smart Agriculture & Food Supply Platform",
            slug = "agritech-platform",
            category = ProjectCategory.AGRICULTURE,
            tagline = "Direct Farm-to-Market Exchange & AI Crop Advisory Ecosystem",
            description = "A technology platform designed to connect smallholder farmers, food suppliers, transporters, and urban consumers to tackle food supply-chain inefficiencies, reduce post-harvest waste, and ensure fair commodity pricing.",
            problemSolved = "Solves post-harvest crop spoilage and middleman price exploitation by providing transparent market price discovery, automated buyer matching, and localized weather/crop advisory.",
            solution = "Comprehensive marketplace and farm telemetry suite empowering smallholder farmers to list produce, secure guaranteed buyer contracts, access USSD offline options, and track cold-chain logistics.",
            technologies = listOf(
                "Python", "Django", "PostgreSQL", "React", "OpenWeather API",
                "Redis", "Chart.js", "SMS/USSD Gateway"
            ),
            imageResId = R.drawable.img_agritech,
            status = "Prototype & Field Pilot",
            featured = true,
            githubUrl = "https://github.com/yardie-del/smart-agritech-ke",
            liveDemoUrl = "https://smartagri.co.ke",
            apkUrl = null,
            date = "2025",
            keyFeatures = listOf(
                "Farmer Crop Listing & Direct Buyer Bidding Marketplace",
                "Real-Time Kenyan Wholesale Market Price Index (Nairobi, Nakuru, Eldoret)",
                "AI-Powered Pest & Disease Diagnostic Suggestions",
                "Localized Hyper-Weather Forecasts & Rain Predictions",
                "Transporter & Cold-Chain Logistics Matching Engine",
                "SMS/USSD Offline Access for Rural Farmers Without Smartphones",
                "Farm Yield Analytics & Soil Health Recording"
            ),
            architecture = "Django backend serving both modern REST endpoints for web/mobile and lightweight text endpoints for Africa's Talking USSD/SMS gateway. PostgreSQL database stores farm records, transactions, and historical price trends.",
            researchIdea = "Researched East African agricultural supply chains where up to 40% of perishable vegetables spoil before reaching urban retail markets.",
            challenges = listOf(
                ChallengeSolution(
                    challenge = "Enabling rural farmers without consistent internet access to participate in trade.",
                    solution = "Integrated USSD and two-way SMS gateway syncing transactions into the centralized PostgreSQL core."
                ),
                ChallengeSolution(
                    challenge = "Fluctuating daily commodity market prices creating price disputes.",
                    solution = "Implemented transparent weighted price aggregation from major Kenyan agricultural hubs."
                )
            ),
            results = "Engineered a working pilot platform supporting both web and USSD interactions with live market price charts.",
            lessonsLearned = "Learned how to design resilient architectures for low-bandwidth environments and integrate multi-channel telecommunication gateways.",
            metrics = listOf(
                "Price Transparency" to "100% Direct",
                "Access Channels" to "Web + USSD",
                "Target Region" to "Kenya / East Africa",
                "Stack" to "Python / Django / USSD"
            ),
            futureRoadmap = listOf(
                "IoT soil moisture and automated drip irrigation controller hardware sync",
                "Micro-insurance smart contracts triggered by drought satellite data",
                "Group purchasing discounts for organic fertilizers and certified seeds"
            ),
            simulatorType = SimulatorType.AGRITECH_EXCHANGE
        ),
        Project(
            id = "cybersecurity-lab",
            title = "Cybersecurity Learning Lab",
            slug = "cybersecurity-lab",
            category = ProjectCategory.CYBERSECURITY,
            tagline = "Practical Defensive Security, Network Auditing & Ethical Testing Sandbox",
            description = "A practical cybersecurity environment for learning networking, Linux, ethical hacking, vulnerability assessment, and defensive security.",
            problemSolved = "Bridges the gap between theoretical computer science and hands-on defense engineering against real-world vulnerabilities (OWASP Top 10, network intrusions, misconfigurations).",
            solution = "A sandbox environment containing structured vulnerability simulation modules, automated network packet scanners, and defensive hardening playbooks.",
            technologies = listOf(
                "Linux", "Kali Linux", "Python", "Bash Scripting",
                "Wireshark", "Nmap", "Metasploit", "Burp Suite", "Docker Labs"
            ),
            imageResId = null,
            status = "Educational / Lab Sandbox",
            featured = true,
            githubUrl = "https://github.com/yardie-del/cybersec-learning-lab",
            liveDemoUrl = "https://github.com/yardie-del/cybersec-learning-lab",
            apkUrl = null,
            date = "2024 - 2026",
            keyFeatures = listOf(
                "Interactive Simulated Terminal for Network Reconnaissance",
                "OWASP Top 10 Web Vulnerability Exploit & Defense Labs",
                "Automated Python Port Scanner & Banner Grabber Tools",
                "Linux Server Hardening & SSH Key Security Automation",
                "Network Packet Sniffing & TLS Certificate Auditing Guide",
                "Ethical Hacking Methodology & Incident Response Playbooks"
            ),
            architecture = "Dockerized isolated container network simulating vulnerable targets and monitoring nodes. Python automation scripts interact with target endpoints to inspect headers, check cipher suites, and report audit scores.",
            researchIdea = "Explored how practical defensive security labs help developers write secure software and prevent common web exploits before deployment.",
            challenges = listOf(
                ChallengeSolution(
                    challenge = "Ensuring strict lab isolation so simulations never leak outside the test environment.",
                    solution = "Implemented isolated bridge Docker networks with zero outbound traffic routing for vulnerable targets."
                ),
                ChallengeSolution(
                    challenge = "Making complex terminal tools intuitive for educational exploration.",
                    solution = "Created an interactive terminal emulator with syntax highlighting, hints, and step-by-step security briefs."
                )
            ),
            results = "Built interactive Python and Bash tools demonstrating vulnerability detection, port auditing, and secure authentication.",
            lessonsLearned = "Gained practical depth in TCP/IP packet structures, symmetric/asymmetric cryptography, and defense-in-depth web application security.",
            metrics = listOf(
                "Lab Scenarios" to "18 Interactive Labs",
                "Security Modules" to "Network, Web, OS, Crypto",
                "Safety Rating" to "100% Isolated Sandbox",
                "Script Language" to "Python & Bash"
            ),
            futureRoadmap = listOf(
                "Cloud infrastructure security misconfiguration scanner (AWS/GCP)",
                "Capture-The-Flag (CTF) tournament engine for student tech clubs",
                "Automated vulnerability report generator producing industry-standard PDF audits"
            ),
            simulatorType = SimulatorType.CYBERSECURITY_TERMINAL
        ),
        Project(
            id = "afripay-hub",
            title = "AfriPay Hub & Payment Gateway",
            slug = "afripay-hub",
            category = ProjectCategory.BACKEND_API,
            tagline = "Unified Mobile Money & Multi-Currency Developer API Gateway",
            description = "A unified payment bridge enabling African startups to seamlessly accept M-Pesa, Airtel Money, card payments, and bank transfers through a single clean REST API.",
            problemSolved = "Eliminates the complexity of integrating multiple fragmented African telco APIs with inconsistent error payloads and varying callback structures.",
            solution = "Single developer-friendly SDK and webhook dispatcher with unified transaction lifecycle, sandbox mock engine, and automated reconciliation dashboard.",
            technologies = listOf("Django REST Framework", "Python", "PostgreSQL", "Redis", "Celery", "Docker"),
            imageResId = null,
            status = "Developer Tooling / MVP",
            featured = true,
            githubUrl = "https://github.com/yardie-del/afripay-gateway",
            liveDemoUrl = "https://github.com/yardie-del/afripay-gateway",
            apkUrl = null,
            date = "2025",
            keyFeatures = listOf(
                "Single REST endpoint for M-Pesa STK, B2C, and C2B",
                "Real-Time Webhook Dispatcher with Replay & Failure Logs",
                "Interactive Developer Sandbox with Simulated Telco Responses",
                "Exportable Financial Reconciliation Reports"
            ),
            architecture = "Django REST API with Celery worker pool and Redis queue handling concurrent transaction requests with cryptographic webhook signature validation.",
            researchIdea = "Identified recurring pain points among East African developers having to re-implement raw M-Pesa Daraja authentication logic for every new project.",
            challenges = listOf(
                ChallengeSolution(
                    challenge = "Telco API downtimes and unexpected timeout statuses.",
                    solution = "Implemented smart polling and auto-query fallback mechanisms with idempotent idempotency keys."
                )
            ),
            results = "Abstracted multi-step telco authentication into a single POST endpoint with under 90ms internal processing overhead.",
            lessonsLearned = "Mastered webhook retry architectures, HMAC-SHA256 signature verification, and secure API key management.",
            metrics = listOf(
                "API Latency" to "85ms",
                "Uptime Target" to "99.95%",
                "Integrations" to "M-Pesa, Airtel, Card"
            ),
            futureRoadmap = listOf(
                "Crypto-to-fiat instant settlement channels",
                "Python and Node.js official SDK packages on PyPI / npm"
            ),
            simulatorType = null
        )
    )

    val skills: List<Skill> = listOf(
        // Frontend
        Skill("React", SkillCategory.FRONTEND, 90, "Project Experience", "Single-page applications, custom hooks, reusable UI systems", "NyumbaLink, ArchConnect KE", "react"),
        Skill("JavaScript", SkillCategory.FRONTEND, 88, "Project Experience", "ES6+, asynchronous programming, DOM manipulation, REST consumption", "Web Apps & SPAs", "javascript"),
        Skill("HTML5 & CSS3", SkillCategory.FRONTEND, 94, "Project Experience", "Semantic markup, responsive layouts, Flexbox, Grid, accessibility", "All Web Projects", "html"),
        Skill("Tailwind CSS", SkillCategory.FRONTEND, 92, "Project Experience", "Utility-first design systems, responsive dark/light themes", "NyumbaLink, ArchConnect KE", "tailwind"),

        // Backend
        Skill("Python", SkillCategory.BACKEND, 94, "Project Experience", "Core programming, automation, scripting, data manipulation", "NyumbaLink, AgriTech, AfriPay", "python"),
        Skill("Django", SkillCategory.BACKEND, 92, "Project Experience", "Monolithic & decoupled backends, ORM, authentication, security", "NyumbaLink, AgriTech Platform", "django"),
        Skill("Django REST Framework", SkillCategory.BACKEND, 92, "Project Experience", "Scalable REST APIs, serializers, JWT auth, viewsets, filters", "NyumbaLink, AfriPay Gateway", "drf"),
        Skill("REST APIs", SkillCategory.BACKEND, 94, "Project Experience", "API contract design, rate-limiting, error handling, documentation", "All Backend Services", "api"),
        Skill("PostgreSQL", SkillCategory.BACKEND, 88, "Project Experience", "Relational database design, indexes, transactions, spatial queries", "NyumbaLink, AgriTech", "postgres"),

        // Mobile
        Skill("Flutter", SkillCategory.MOBILE, 82, "Project Experience", "Cross-platform mobile interfaces, state management, REST consumption", "Mobile Prototypes", "flutter"),
        Skill("Android & Kotlin", SkillCategory.MOBILE, 88, "Project Experience", "Native Android development, Jetpack Compose, Room persistence, MVVM", "Kings Portfolio App", "kotlin"),
        Skill("Jetpack Compose", SkillCategory.MOBILE, 88, "Project Experience", "Declarative modern UI, animations, custom draw modifiers, Material 3", "Kings Portfolio App", "compose"),

        // Tools & DevOps
        Skill("Git", SkillCategory.TOOLS, 90, "Project Experience", "Version control, branching workflows, merging, conflict resolution", "All GitHub Repositories", "git"),
        Skill("GitHub", SkillCategory.TOOLS, 90, "Project Experience", "Repository management, open-source collaboration, issue tracking", "yardie-del GitHub Profile", "github"),
        Skill("Docker", SkillCategory.TOOLS, 82, "Familiar", "Containerization, Docker Compose for local development & databases", "NyumbaLink & Lab Containers", "docker"),
        Skill("Linux", SkillCategory.TOOLS, 88, "Project Experience", "Ubuntu/Debian server administration, CLI navigation, bash automation", "CyberSec Lab & Server Setups", "linux"),
        Skill("Firebase", SkillCategory.TOOLS, 80, "Familiar", "Cloud Firestore, Firebase Storage, push notifications", "Mobile Backend Integration", "firebase"),
        Skill("VS Code", SkillCategory.TOOLS, 94, "Project Experience", "Daily development environment, debugging, linters, extensions", "Daily Workflow", "vscode"),

        // Foundations & Security
        Skill("OWASP Top 10 Fundamentals", SkillCategory.OTHER_FOUNDATIONS, 85, "Project Experience", "XSS prevention, SQL injection avoidance, secure headers, CSRF", "Cybersecurity Learning Lab", "security"),
        Skill("Secure Authentication & APIs", SkillCategory.OTHER_FOUNDATIONS, 88, "Project Experience", "JWT tokens, password hashing (bcrypt/Argon2), rate-limiting", "NyumbaLink, AfriPay", "auth"),
        Skill("Database Architecture", SkillCategory.OTHER_FOUNDATIONS, 88, "Project Experience", "Normalization, schema design, constraints, relational integrity", "PostgreSQL Schemas", "database"),
        Skill("UI/UX & Accessibility", SkillCategory.OTHER_FOUNDATIONS, 86, "Project Experience", "User flow design, high-contrast theming, touch targets, screen readers", "Kings Portfolio & Web Apps", "uiux"),
        Skill("Problem Solving & Algorithms", SkillCategory.OTHER_FOUNDATIONS, 88, "Project Experience", "Data structures, algorithmic efficiency, clean code architecture", "BSc IT University Studies", "algo")
    )

    val howIBuildSteps: List<EngineeringStep> = listOf(
        EngineeringStep(
            stepNumber = 1,
            title = "Problem Discovery",
            subtitle = "Understanding the Core Friction",
            description = "Identify the real user friction, business goals, and operational bottlenecks before writing a single line of code. Frame the challenge in measurable terms.",
            keyPractices = listOf("Stakeholder Q&A", "User Pain Point Mapping", "Scope Definition", "Success Metrics"),
            iconName = "search"
        ),
        EngineeringStep(
            stepNumber = 2,
            title = "Research & Feasibility",
            subtitle = "Benchmarking & Toolchain Selection",
            description = "Analyze existing market alternatives, evaluate technical feasibility, verify data availability, and choose the most pragmatic tech stack.",
            keyPractices = listOf("Market Comparison", "Tech Stack Evaluation", "Cost & Resource Estimation", "Risk Assessment"),
            iconName = "library"
        ),
        EngineeringStep(
            stepNumber = 3,
            title = "UI/UX Design",
            subtitle = "Wireframing & User Journeys",
            description = "Map out intuitive user flows, create responsive wireframes, design component hierarchies, and ensure accessibility (touch targets, contrast, clarity).",
            keyPractices = listOf("User Flow Diagrams", "Component Hierarchy", "Mobile-First Wireframes", "Accessibility Check"),
            iconName = "design"
        ),
        EngineeringStep(
            stepNumber = 4,
            title = "Architecture & Schema",
            subtitle = "Data Modeling & Contract Design",
            description = "Design relational database schemas, define REST/API contracts, establish authentication rules, and model service boundaries.",
            keyPractices = listOf("ERD Database Schemas", "API Endpoint Contracts", "Security Threat Modeling", "Data Flow Specs"),
            iconName = "architecture"
        ),
        EngineeringStep(
            stepNumber = 5,
            title = "Development",
            subtitle = "Clean, Modular Implementation",
            description = "Write clean, readable, modular code adhering to MVVM/Clean Architecture patterns. Practice atomic git commits with clear descriptive messages.",
            keyPractices = listOf("Modular Components", "Type Safety", "State Hoisting / MVVM", "Git Version Control"),
            iconName = "code"
        ),
        EngineeringStep(
            stepNumber = 6,
            title = "Testing & QA",
            subtitle = "Verification & Edge-Case Handling",
            description = "Test API responses with Postman, write unit tests for critical business logic, verify error states, and test cross-device responsiveness.",
            keyPractices = listOf("API Validation", "Unit & UI Tests", "Error Boundary Handling", "Cross-Device Testing"),
            iconName = "test"
        ),
        EngineeringStep(
            stepNumber = 7,
            title = "Deployment",
            subtitle = "CI/CD & Cloud Infrastructure",
            description = "Containerize services with Docker, set up automated build checks, configure SSL/TLS encryption, and deploy to stable cloud environments.",
            keyPractices = listOf("Docker Containerization", "Environment Secrets Isolation", "SSL/TLS Security", "Production Builds"),
            iconName = "cloud"
        ),
        EngineeringStep(
            stepNumber = 8,
            title = "Maintenance & Iteration",
            subtitle = "Monitoring & Continuous Improvement",
            description = "Monitor system health, analyze error logs, gather direct user feedback, and iterate with performance enhancements and feature updates.",
            keyPractices = listOf("Error Log Monitoring", "User Feedback Loops", "Performance Profiling", "Continuous Refactoring"),
            iconName = "build"
        )
    )

    val cyberSecurityTopics: List<CyberSecurityTopic> = listOf(
        CyberSecurityTopic(
            id = "sec-linux",
            title = "Linux Server Hardening",
            domain = "Operating System & Infrastructure",
            description = "Configuring secure Linux server environments with strict permission controls, SSH key-only access, firewall rules (UFW), and automated security patches.",
            practices = listOf("SSH Key Authentication (No root passwords)", "UFW Firewall Port Whitelisting", "Non-Root Service Execution", "Log Auditing (/var/log/auth.log)"),
            iconName = "terminal"
        ),
        CyberSecurityTopic(
            id = "sec-auth",
            title = "Secure Authentication & Sessions",
            domain = "Identity & Access Management",
            description = "Implementing robust user authentication with cryptographic password hashing (Argon2/bcrypt), short-lived JWT tokens, and refresh token rotation.",
            practices = listOf("Argon2 / bcrypt Salting & Hashing", "HttpOnly / SameSite Secure Cookies", "Rate-Limiting Login Attempts", "Role-Based Access Control (RBAC)"),
            iconName = "lock"
        ),
        CyberSecurityTopic(
            id = "sec-owasp",
            title = "OWASP Top 10 Web Defense",
            domain = "Application Security",
            description = "Defending web applications against common vulnerabilities: SQL Injection, Cross-Site Scripting (XSS), Cross-Site Request Forgery (CSRF), and Broken Access Control.",
            practices = listOf("Parameterized SQL / ORM Queries", "Context-Aware HTML Escaping", "CSRF Token Validation", "Strict Input Sanitization"),
            iconName = "shield"
        ),
        CyberSecurityTopic(
            id = "sec-api",
            title = "API Security & Rate Limiting",
            domain = "Network & Endpoint Defense",
            description = "Securing REST endpoints against denial-of-service, parameter tampering, data scraping, and unauthorized access through throttling and signature verification.",
            practices = listOf("Token-Bucket Rate Limiting", "HMAC Webhook Signatures", "CORS Strict Domain Policies", "Payload Size Restrictions"),
            iconName = "api"
        ),
        CyberSecurityTopic(
            id = "sec-network",
            title = "Network Auditing & Reconnaissance",
            domain = "Network Security",
            description = "Performing structured ethical network reconnaissance using Nmap, inspecting packet flows with Wireshark, and auditing SSL/TLS cipher suites.",
            practices = listOf("Port & Service Banner Scanning", "TLS 1.3 / Cipher Suite Audits", "Packet Inspection & Protocol Analysis", "DNS & Subdomain Enumeration"),
            iconName = "wifi"
        ),
        CyberSecurityTopic(
            id = "sec-coding",
            title = "Secure Coding Practices",
            domain = "Software Engineering",
            description = "Adhering to secure development lifecycles: never hardcoding secrets, using environment variables, conducting peer code reviews, and dependency vulnerability scanning.",
            practices = listOf("Secrets in Environment Variables (.env)", "Dependency Vulnerability Audits", "Graceful Error Handling (No stack traces to users)", "Least-Privilege Principle"),
            iconName = "code"
        )
    )

    val cyberSecurityLabs: List<CyberSecurityLab> = listOf(
        CyberSecurityLab(
            id = "lab-auth-ratelimit",
            title = "Lab 1: Authentication Security & Brute-Force Rate Limiting",
            objective = "Protect login endpoints from automated credential stuffing and brute-force password cracking attacks.",
            conceptExplained = "Attackers use automated bots to attempt thousands of password combinations per minute. Without rate limiting or exponential lockouts, weak passwords can be cracked quickly.",
            defenseStrategy = "Implement IP and username based rate limiting (e.g. max 5 attempts per 15 minutes), progressive delay penalties, and captcha/2FA challenge escalation.",
            implementationExample = "In Django REST Framework: configure `AnonRateThrottle` and `UserRateThrottle` with Redis cache backends to enforce `5/min` limits on `/api/v1/auth/login/`."
        ),
        CyberSecurityLab(
            id = "lab-sqli-prevention",
            title = "Lab 2: SQL Injection Prevention & Parameterized Queries",
            objective = "Prevent malicious SQL commands from altering database queries and extracting unauthorized data.",
            conceptExplained = "Concatenating unvalidated user input directly into SQL strings allows attackers to inject clauses like `' OR '1'='1` to bypass authentication or dump database tables.",
            defenseStrategy = "Always use parameterized prepared statements or high-level ORMs (Django ORM, Room SQLite queries with @Query arguments) where parameters are strictly treated as data, not executable code.",
            implementationExample = "Safe: `db.query('SELECT * FROM users WHERE email = :email', email)` vs Unsafe: `'SELECT * FROM users WHERE email = ' + email`."
        ),
        CyberSecurityLab(
            id = "lab-password-hashing",
            title = "Lab 3: Cryptographic Password Hashing (Argon2 / bcrypt)",
            objective = "Securely store user credentials so that database breaches never compromise plain-text passwords.",
            conceptExplained = "Fast hashing algorithms like MD5 or SHA1 are vulnerable to rainbow table lookups and GPU cracking. Modern authentication requires slow, salted, memory-hard key derivation functions.",
            defenseStrategy = "Use Argon2id or bcrypt with unique cryptographic salts and sufficient work factors (cost >= 12), ensuring each password takes milliseconds to compute.",
            implementationExample = "In Python: use `passlib.hash.argon2.using(rounds=4, memory_cost=102400).hash(password)` to compute memory-hard salt-infused hashes."
        ),
        CyberSecurityLab(
            id = "lab-jwt-lifecycle",
            title = "Lab 4: Secure API Token Lifecycle (JWT & Refresh Token Rotation)",
            objective = "Implement stateless API authentication while preventing token theft and replay attacks.",
            conceptExplained = "Long-lived JWT access tokens stored in browser local storage are vulnerable to XSS theft and cannot be easily revoked before expiration.",
            defenseStrategy = "Issue short-lived access tokens (10-15 minutes) and store refresh tokens in secure HttpOnly SameSite cookies with automated single-use rotation on each refresh.",
            implementationExample = "On `/api/token/refresh/`: validate current refresh token, invalidate it immediately, and issue a brand-new token pair to prevent reuse attacks."
        ),
        CyberSecurityLab(
            id = "lab-nmap-scanning",
            title = "Lab 5: Network Service Auditing & Port Reconnaissance",
            objective = "Audit exposed network services and identify unauthorized listening ports on production servers.",
            conceptExplained = "Unused listening services (e.g. exposed Redis instances, telnet, unhardened database ports) provide attack vectors for unauthorized remote code execution.",
            defenseStrategy = "Perform regular Nmap audits (`nmap -sV -sC -p- <server-ip>`), bind internal services strictly to `127.0.0.1`, and enforce default-deny firewall policies.",
            implementationExample = "Run `nmap -sS -p 22,80,443,5432,6379 192.168.1.1` to confirm PostgreSQL (5432) and Redis (6379) are closed to external internet interfaces."
        )
    )

    val achievements: List<Achievement> = listOf(
        Achievement(
            id = "ach-degree",
            title = "BSc in Information Technology",
            category = AchievementType.EDUCATION,
            issuerOrContext = "University in Kenya",
            dateOrYear = "2022 - Present (3rd Year)",
            description = "Pursuing comprehensive studies in software engineering, algorithms, database management, network security, operating systems, and computer architecture.",
            tags = listOf("BSc IT", "Computer Science", "Algorithms", "Databases"),
            iconName = "school"
        ),
        Achievement(
            id = "ach-nyumbalink",
            title = "Architected & Shipped NyumbaLink Flagship PropTech Platform",
            category = AchievementType.PROJECT_LAUNCH,
            issuerOrContext = "NyumbaLink Tech",
            dateOrYear = "2025",
            description = "Engineered a full-stack housing platform connecting Kenyan tenants with verified landlords, featuring GPS maps, M-Pesa payments, and landlord analytics.",
            linkUrl = "https://github.com/yardie-del/nyumbalink-backend",
            tags = listOf("PropTech", "Full-Stack", "Django REST", "React", "M-Pesa"),
            iconName = "rocket"
        ),
        Achievement(
            id = "ach-kings-portfolio",
            title = "Developed Kings Portfolio Native Android Platform",
            category = AchievementType.PROJECT_LAUNCH,
            issuerOrContext = "Independent Engineering",
            dateOrYear = "2026",
            description = "Engineered high-performance native Android application with Kotlin, Jetpack Compose, Material 3, Room local database, and live GitHub REST API telemetry.",
            linkUrl = "https://github.com/yardie-del/kings-portfolio",
            tags = listOf("Android", "Kotlin", "Jetpack Compose", "Room", "Material 3"),
            iconName = "android"
        ),
        Achievement(
            id = "ach-cybersec-lab",
            title = "Built Cybersecurity Learning Lab & Open Source Tooling",
            category = AchievementType.OPEN_SOURCE,
            issuerOrContext = "GitHub Open Source",
            dateOrYear = "2024 - 2026",
            description = "Developed hands-on educational security test beds covering OWASP Top 10 vulnerabilities, network port scanners, and Linux server hardening playbooks.",
            linkUrl = "https://github.com/yardie-del/cybersec-learning-lab",
            tags = listOf("Cybersecurity", "Python", "Linux", "OWASP", "Educational"),
            iconName = "shield"
        ),
        Achievement(
            id = "ach-github-activity",
            title = "Active Open-Source Builder & Contributor",
            category = AchievementType.OPEN_SOURCE,
            issuerOrContext = "GitHub (@yardie-del)",
            dateOrYear = "Ongoing",
            description = "Maintains public repositories spanning web development, backend APIs, mobile prototypes, and African digital infrastructure solutions.",
            linkUrl = "https://github.com/yardie-del",
            tags = listOf("GitHub", "yardie-del", "Open Source", "Repositories"),
            iconName = "hub"
        ),
        Achievement(
            id = "ach-agritech",
            title = "Designed Smart Agriculture & Food Supply Platform",
            category = AchievementType.PROJECT_LAUNCH,
            issuerOrContext = "African Innovation Series",
            dateOrYear = "2025",
            description = "Created direct farmer-to-buyer marketplace architecture with wholesale price tracking and SMS/USSD offline access for Kenyan rural communities.",
            linkUrl = "https://github.com/yardie-del/smart-agritech-ke",
            tags = listOf("AgriTech", "Kenya", "USSD", "Food Security", "Python"),
            iconName = "eco"
        )
    )

    val testimonials: List<Testimonial> = emptyList()

    val featuredRepos: List<GitHubRepo> = listOf(
        GitHubRepo(
            name = "kings-portfolio",
            description = "High-performance native Android developer portfolio application crafted with Kotlin, Jetpack Compose, Material 3, and GitHub API integration.",
            language = "Kotlin",
            stars = 42,
            forks = 12,
            isFeatured = true,
            updatedAgo = "Active",
            tags = listOf("android", "kotlin", "jetpack-compose", "material3", "portfolio")
        ),
        GitHubRepo(
            name = "nyumbalink-backend",
            description = "Core backend REST API for NyumbaLink PropTech platform powered by Django, PostgreSQL, Redis and M-Pesa Daraja integration.",
            language = "Python",
            stars = 34,
            forks = 9,
            isFeatured = true,
            updatedAgo = "Active",
            tags = listOf("django", "drf", "postgresql", "proptech", "mpesa")
        ),
        GitHubRepo(
            name = "archconnect-ke",
            description = "Architecture marketplace connecting Kenyan property developers, licensed firms, registered architects, and students.",
            language = "React / Python",
            stars = 24,
            forks = 6,
            isFeatured = true,
            updatedAgo = "Active",
            tags = listOf("architecture", "django", "react", "marketplace", "kenya")
        ),
        GitHubRepo(
            name = "smart-agritech-ke",
            description = "Agricultural supply chain and direct farmer-to-buyer marketplace with SMS/USSD fallback and price discovery.",
            language = "Python / React",
            stars = 22,
            forks = 4,
            isFeatured = true,
            updatedAgo = "Active",
            tags = listOf("agritech", "django", "ussd", "react", "kenya")
        ),
        GitHubRepo(
            name = "cybersec-learning-lab",
            description = "Practical cybersecurity lab scripts, network scanning tools, Linux hardening playbooks, and vulnerability test beds.",
            language = "Python / Bash",
            stars = 19,
            forks = 3,
            isFeatured = false,
            updatedAgo = "Active",
            tags = listOf("cybersecurity", "linux", "nmap", "hardening", "ethical-hacking")
        ),
        GitHubRepo(
            name = "afripay-gateway",
            description = "Unified payment bridge API for African telco mobile money integrations (M-Pesa, Airtel, Cards).",
            language = "Python",
            stars = 16,
            forks = 2,
            isFeatured = false,
            updatedAgo = "Active",
            tags = listOf("fintech", "mpesa-daraja", "rest-api", "celery")
        )
    )

    val milestones: List<Milestone> = listOf(
        Milestone(
            id = "m1",
            year = "2022",
            title = "Began BSc in Information Technology",
            subtitle = "Starting the Academic & Engineering Foundation",
            description = "Enrolled in 3rd-year BSc Information Technology in Kenya. Dove deep into programming fundamentals, data structures, algorithms, discrete mathematics, and database management.",
            tags = listOf("Python", "Java", "C++", "Algorithms", "Database Systems")
        ),
        Milestone(
            id = "m2",
            year = "2023",
            title = "Built First Full-Stack Web & Mobile Applications",
            subtitle = "Bridging Frontend and Backend",
            description = "Mastered React, Tailwind CSS, and Flutter. Developed practical client-side applications, learning responsive design principles and mobile UI development.",
            tags = listOf("React", "Flutter", "Tailwind CSS", "JavaScript")
        ),
        Milestone(
            id = "m3",
            year = "2024",
            title = "Mastered Scalable Backends, APIs & Databases",
            subtitle = "Building Robust Production Infrastructure",
            description = "Adopted Django and Django REST Framework for production backends. Engineered complex relational databases in PostgreSQL, integrated Redis caching, and built Celery background workers.",
            tags = listOf("Django", "DRF", "PostgreSQL", "Redis", "Celery", "REST APIs")
        ),
        Milestone(
            id = "m4",
            year = "2024",
            title = "Established Cybersecurity Learning Lab",
            subtitle = "Defensive Engineering & Network Auditing",
            description = "Established the Cybersecurity Learning Lab to gain practical hands-on experience in Linux hardening, vulnerability scanning, network traffic analysis, and OWASP defense.",
            tags = listOf("Linux", "Kali Linux", "Wireshark", "Network Security", "Ethical Hacking")
        ),
        Milestone(
            id = "m5",
            year = "2025",
            title = "Architected NyumbaLink & ArchConnect KE",
            subtitle = "Solving Real Kenyan Real Estate & Architecture Challenges",
            description = "Built NyumbaLink from the ground up to eliminate scam brokers in Kenya, and architected ArchConnect KE for architectural project bidding and portfolio verification.",
            tags = listOf("NyumbaLink", "ArchConnect", "PropTech", "M-Pesa API", "Full-Stack"),
            isKeyAchievement = true
        ),
        Milestone(
            id = "m6",
            year = "2025 - 2026",
            title = "Engineered Kings Portfolio & Smart AgriTech",
            subtitle = "Native Mobile Development & African Agricultural Solutions",
            description = "Developed the Kings Portfolio native Android application with Kotlin and Jetpack Compose, and piloted the Smart AgriTech platform connecting farmers with wholesale buyers.",
            tags = listOf("Android", "Kotlin", "Compose", "AgriTech", "Innovation"),
            isKeyAchievement = true
        ),
        Milestone(
            id = "m7",
            year = "2026",
            title = "Available for High-Impact Software Engineering & Client Projects",
            subtitle = "Collaborating & Building Real Products",
            description = "Active in collaborating with founders, tech companies, open-source projects, and engineering teams to build impactful digital solutions for Africa and the world.",
            tags = listOf("Software Developer", "Full-Stack", "Mobile", "Kenya 🇰🇪"),
            isKeyAchievement = true
        )
    )

    val services: List<ServiceOffering> = listOf(
        ServiceOffering(
            id = "s1",
            title = "Web Applications",
            shortDesc = "Modern full-stack web applications and interactive dashboards.",
            fullDesc = "End-to-end responsive web applications built with React, Tailwind CSS, and modern web standards. High-speed performance, SEO-friendly architecture, and intuitive user experiences.",
            deliverables = listOf(
                "Custom Responsive Web Apps",
                "Admin & Analytics Dashboards",
                "High-Converting Landing Pages",
                "Single-Page Applications (SPAs)"
            ),
            techStack = listOf("React", "Tailwind CSS", "JavaScript", "HTML5", "Vite"),
            iconName = "web"
        ),
        ServiceOffering(
            id = "s2",
            title = "Mobile Applications",
            shortDesc = "Cross-platform mobile applications using Flutter and Jetpack Compose.",
            fullDesc = "Fast, native-feeling mobile applications with smooth gestures, offline caching, push notifications, and clean Material 3 design.",
            deliverables = listOf(
                "Native Android Apps (Kotlin/Compose)",
                "Cross-Platform iOS & Android Apps (Flutter)",
                "Clean Material 3 UI/UX Design",
                "Offline Storage & Local Caching (Room/SQLite)"
            ),
            techStack = listOf("Kotlin", "Jetpack Compose", "Flutter", "Dart", "Room / SQLite"),
            iconName = "mobile"
        ),
        ServiceOffering(
            id = "s3",
            title = "Backend & APIs",
            shortDesc = "Secure REST APIs and backend systems using Django and Python.",
            fullDesc = "Production-grade backend architectures, RESTful API design, token authentication (JWT/OAuth), role-based permissions, and asynchronous task queues.",
            deliverables = listOf(
                "RESTful & Microservice API Systems",
                "Payment Integrations (M-Pesa Daraja, Stripe)",
                "Authentication & Security Layers (JWT, Argon2)",
                "Background Task Queues (Celery/Redis)"
            ),
            techStack = listOf("Django", "Django REST Framework", "Python", "Celery", "Redis"),
            iconName = "backend"
        ),
        ServiceOffering(
            id = "s4",
            title = "Database Systems",
            shortDesc = "Reliable database architectures using PostgreSQL and related technologies.",
            fullDesc = "Relational data modeling, query optimization, spatial queries with PostGIS, database migrations, connection pooling, and automated backups.",
            deliverables = listOf(
                "Schema & Entity Relationship Design",
                "Index Optimization & Slow Query Tuning",
                "Spatial Geodata Queries (PostGIS)",
                "Data Migration & Integrity Checks"
            ),
            techStack = listOf("PostgreSQL", "PostGIS", "Redis", "SQLite", "SQL Optimization"),
            iconName = "database"
        ),
        ServiceOffering(
            id = "s5",
            title = "Cybersecurity & Hardening",
            shortDesc = "Defensive security audits, Linux hardening, and secure coding.",
            fullDesc = "Reviewing application security against OWASP vulnerabilities, setting up rate limits, securing authentication workflows, and hardening Linux servers.",
            deliverables = listOf(
                "OWASP Top 10 Web Vulnerability Audits",
                "Linux Server SSH & Firewall Hardening",
                "API Security & Rate Limiting Setup",
                "Secure Authentication & Password Hashing"
            ),
            techStack = listOf("Linux", "Python", "Nmap", "Wireshark", "Bash"),
            iconName = "security"
        ),
        ServiceOffering(
            id = "s6",
            title = "Full-Stack MVP Development",
            shortDesc = "End-to-end MVP development for startups and entrepreneurs.",
            fullDesc = "Turning early-stage startup ideas into functional, market-ready MVPs. Architecture planning, rapid development, payment readiness, and deployment to the cloud.",
            deliverables = listOf(
                "Rapid MVP Prototype to Production",
                "Product Architecture & Roadmapping",
                "Docker Cloud Deployment",
                "Continuous Integration & GitHub Actions"
            ),
            techStack = listOf("Full Stack", "Docker", "Linux", "Git/GitHub", "M-Pesa"),
            iconName = "startup"
        )
    )

    val quickStats = listOf(
        "BSc IT" to "3rd Year @ Kenya",
        "Projects" to "6+ Production & Labs",
        "Core Stack" to "Python, Django, React, Kotlin",
        "Focus" to "African Tech Innovation 🇰🇪"
    )
}
