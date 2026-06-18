# 🔑 Secret scanning test fixtures

Every credential in this folder is **fake / randomly generated** and non-functional.
They exist only so GitHub **secret scanning** has something to detect.

Two kinds are included:

| Type | Examples | Detector |
| ---- | -------- | -------- |
| **Default / partner patterns** | AWS, GitHub PAT, Slack, Stripe, Google API, SendGrid, Twilio, private keys | Built-in provider detectors |
| **Generic secrets** | generic passwords, generic API keys, connection strings | Generic / push-protection detectors |

> After you copy this repo into a **public** repository, enable
> *Settings → Advanced Security → Secret scanning* (and **push protection**) and
> these will surface as alerts on the **Security** tab.

## ⚠️ Push-protection–enforced patterns

Four provider patterns — **Slack, Stripe, Mailgun, and Twilio** — are enforced by
**push protection** and cannot be committed directly (your push is blocked). They are
shipped here as `…PLACEHOLDER…` values. To test these live, replace a placeholder with a
real-format fake token; the commit will be blocked, and you can use the one-click
**“allow secret”** bypass link printed by `git push` to let it through.
