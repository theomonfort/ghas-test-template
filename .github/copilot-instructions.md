# Copilot custom instructions

This repository is an **intentionally vulnerable demo** used to showcase
GitHub Advanced Security (CodeQL code scanning, Copilot Autofix, secret
scanning, Dependabot, and Copilot Code Review).

When reviewing or fixing code here:

- Do **not** dismiss a finding as "just a demo" — explain the real-world impact
  and the recommended secure fix.
- Prefer the **most idiomatic secure pattern** for the language (parameterized
  queries, bounds-checked buffers, safe deserialization, CSPRNG, etc.).
- Reference the relevant **CWE** number when applicable (e.g. `CWE-89` for SQLi,
  `CWE-120` for buffer overflow, `CWE-798` for hard-coded credentials).
- Never copy any pattern from this repository into production code.
