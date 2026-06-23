# ghas-test-template

> ⚠️ **WARNING — Intentionally Vulnerable Code** ⚠️
>
> This repository contains deliberately insecure code, hard-coded secrets, vulnerable
> dependencies, and other security anti-patterns. It exists **only** to demonstrate and
> test GitHub Advanced Security (GHAS). **Do NOT** deploy any of this code or copy its
> patterns into real projects.

## 🚀 Use this template

This is a GitHub **template repository**. Click **"Use this template" → "Create a new
repository"** to get your own copy in seconds.

> 💡 **GHAS is free on public repositories.** If you create your copy as a **public**
> repo, you can turn on the GHAS tools — **CodeQL code scanning, secret scanning + push
> protection, and Dependabot** — at no cost and start seeing alerts right away.

> 🏢 **To fully leverage GHAS you need a paid GitHub Enterprise plan with GitHub
> Advanced Security (Code Security + Secret Protection).** On **private** repositories,
> and for the full feature set — **code quality / code scanning on private repos,
> generic (non-provider) secret detection, security overview dashboards, and
> enterprise-wide policies** — a paid Enterprise + GHAS subscription is required. The
> free tier on **public** repos covers the basics demonstrated here.

## What you can test

| GHAS tool | What to look for |
| --------- | ---------------- |
| 🔍 **CodeQL** code scanning (SAST) | Alerts across C, C++, Java, JavaScript, Python on the **Security** tab |
| 🧹 **Code quality** | Maintainability/reliability findings from `quality_smells.*` (run with the `security-and-quality` suite) |
| 📊 **Code coverage** | Native Code Quality coverage — `coverage_demo/` + `tests/` produce a Cobertura report uploaded on PRs (requires Code Quality enabled) |
| 🤖 **Copilot Autofix** | Suggested fixes on CodeQL alerts |
| 👁️ **Copilot Code Review** | Open a PR touching a vulnerable file |
| 🔑 **Secret scanning** + push protection | Fake credentials in [`secrets/`](secrets/) |
| 📦 **Dependabot** | Alerts/updates for the vulnerable manifests |

## Layout

```
src/
├── c/            # C vulnerabilities (buffer overflow, command injection, UAF, …)
├── cpp/          # C++ vulnerabilities (buffer overflow, SQLi, path traversal, …)
├── javascript/   # Node.js vulnerabilities (SQLi, XSS, SSRF, proto pollution, …)
├── python/       # Python vulnerabilities (pickle, eval, XXE, command injection, …)
└── java/         # Java vulnerabilities (log4shell-era deps, XXE, deserialization, …)

secrets/          # Fake credentials for secret scanning (default + generic patterns)

.github/
├── workflows/codeql.yml      # CodeQL analysis for all 5 languages
├── copilot-instructions.md   # Custom Copilot instructions
└── dependabot.yml            # Dependabot config
```

## How to use (after creating your public repo)

1. Go to **Settings → Advanced Security** and enable:
   - **Secret scanning** (+ **Push protection**)
   - **Dependabot** alerts & security updates
2. Code scanning runs automatically via the **CodeQL** Actions workflow on push to `main`.
3. Open the **Security** tab to view alerts.
4. Try **Copilot Autofix** on a CodeQL alert.
5. Open a PR that touches a vulnerable file to see **Copilot Code Review** in action.

## 🔑 Secrets

The [`secrets/`](secrets/) folder contains **fake, non-functional** credentials so secret
scanning has something to detect — both **default/partner patterns** (AWS, GitHub PAT,
Slack, Stripe, Google, SendGrid, Twilio, private keys) and **generic secrets** (generic
passwords, API keys, connection strings).

## Vulnerability categories (by language)

| Category                     | C | C++ | JS | Python | Java |
| ---------------------------- | - | --- | -- | ------ | ---- |
| Buffer overflow              | ✅ | ✅  | —  | —      | —    |
| SQL injection                | — | ✅  | ✅ | ✅     | ✅   |
| Command injection            | ✅ | ✅  | ✅ | ✅     | ✅   |
| Path traversal               | ✅ | ✅  | ✅ | ✅     | ✅   |
| Format string                | ✅ | ✅  | —  | —      | —    |
| Integer overflow             | ✅ | ✅  | —  | —      | —    |
| Use-after-free / double free | ✅ | ✅  | —  | —      | —    |
| Insecure randomness          | ✅ | —   | ✅ | —      | ✅   |
| Cross-site scripting (XSS)   | — | —   | ✅ | ✅     | ✅   |
| SSRF                         | — | —   | ✅ | ✅     | ✅   |
| Insecure deserialization     | — | —   | ✅ | ✅     | ✅   |
| Weak cryptography            | — | —   | ✅ | ✅     | ✅   |
| Hard-coded secrets           | ✅ | ✅  | ✅ | ✅     | ✅   |
| Vulnerable dependencies      | — | —   | ✅ | ✅     | ✅   |
