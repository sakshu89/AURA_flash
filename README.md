# AURA Flash

Android project with an automated GitHub Actions build.

## What is here

- `app/` — minimal Gradle Android project with a working launcher screen (`MainActivity.java`) using the AURA palette.
- `LiquidGlass.kt`, `LyricsTheme.kt`, `PlayerControls.kt`, `SettingsCommonComponents.kt`, `SettingsScreen.kt`, `Theme*.kt` — original Compose UI sources from the larger LastWave app, kept here for reference. They reference classes that are not in this repository, so they are **not** part of the compiled app.
- Root `colors.xml`, `strings.xml`, `themes.xml` — original resource files; copies adapted for this app live under `app/src/main/res/values/`.
- `.github/workflows/build-aura.yml` — builds and uploads a debug APK on every push and on demand.

## Build locally

```sh
gradle :app:assembleDebug
```

APK: `app/build/outputs/apk/debug/app-debug.apk`

## Download an APK

Open the **Actions** tab, choose the latest successful **Build AURA Flash APK** run, and download the `AURA-Flash-debug-apk` artifact. It is a ZIP — extract it and install the inner `app-debug.apk`.
