#!/data/data/com.termux/files/usr/bin/bash

# Create directory if it doesn't exist
mkdir -p .github/workflows

# Overwrite the android.yml with optimized settings
cat << 'WORKFLOW' > .github/workflows/android.yml
name: Build Fait
on: [push]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: gradle

      - name: Grant execute permission for gradlew
        run: chmod +x gradlew

      - name: Build with Gradle
        # Optimized for GitHub memory limits to prevent Exit Code 137
        run: ./gradlew assembleDebug --no-daemon -Dorg.gradle.jvmargs="-Xmx4g -XX:MaxMetaspaceSize=1g"

      - name: Upload APK
        uses: actions/upload-artifact@v4
        with:
          name: fait-debug-apk
          path: app/build/outputs/apk/debug/app-debug.apk
WORKFLOW

echo "Workflow file updated successfully."
