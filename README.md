# 🧹 CacheCleaner (Android API 17)

![CacheCleaner Logo](https://img.shields.io/badge/Android-API%2017+-3DDC84?style=for-the-badge&logo=android)
![Version](https://img.shields.io/badge/Version-1.0.0-blue?style=for-the-badge)

**CacheCleaner** est une application Android ultra-légère conçue spécifiquement pour cibler l'API Android 17 (Jelly Bean 4.2). Son but unique est de vider automatiquement le cache de **toutes** les applications (système et utilisateur) de votre appareil dès son lancement.

L'application arbore une interface utilisateur moderne (Material Design) offrant un retour visuel clair pendant le nettoyage.

---

## ✨ Fonctionnalités Principales

- 🚀 **100% Automatique :** Lancez l'application, elle s'occupe de tout et se ferme d'elle-même.
- 🧹 **Nettoyage Global :** Vide le cache des applications système et utilisateur.
- 🎨 **Interface Moderne :** Utilise `MaterialCardView` et une palette de couleurs soignée, prouvant que même une application API 17 peut être élégante.
- ⚡ **Ultra-Rapide :** Le nettoyage est instancié immédiatement via les API internes d'Android.

---

## 🛠️ Comment ça marche ? (La technique)

Sur les anciennes versions d'Android (comme l'API 17), il n'existait pas de méthode publique documentée pour effacer le cache de toutes les applications en une seule fois sans l'intervention de l'utilisateur.

Cette application contourne cette limitation en utilisant la **Réflexion Java** (`java.lang.reflect`).
Elle va chercher et invoquer la méthode cachée `freeStorageAndNotify` de la classe interne `PackageManager`.
En passant la valeur `Long.MAX_VALUE` (la taille maximale d'un `long`), on force le système d'exploitation à libérer autant d'espace que possible, ce qui le pousse naturellement à supprimer tous les fichiers de cache disponibles.

*Note: Cette astuce nécessite la permission spéciale `android.permission.CLEAR_APP_CACHE` déclarée dans le Manifest.*

---

## 📦 Installation et Compilation

### Prérequis
- [Android Studio](https://developer.android.com/studio) ou le SDK Android avec Gradle (version 8.0 supportée par le wrapper inclus).
- Un appareil ou un émulateur exécutant au minimum Android 4.2 (API 17).

### Compilation en ligne de commande
1. Clonez ce dépôt sur votre machine.
2. Naviguez dans le dossier `android_app` :
   ```bash
   cd android_app
   ```
3. Lancez la compilation via Gradle Wrapper :
   ```bash
   ./gradlew assembleDebug
   ```
4. L'APK généré se trouvera dans `app/build/outputs/apk/debug/app-debug.apk`.

### Installation sur un appareil
Connectez votre appareil avec le débogage USB activé et exécutez :
```bash
./gradlew installDebug
```

---

## 🎨 Captures d'écran (Concept)
Au lancement de l'application, vous verrez une carte moderne et élégante centrée sur l'écran :

> ⏳ **Nettoyage en cours...**
> *Optimisation du système*

L'application affichera une petite notification (Toast) indiquant "Nettoyage terminé" avant de se refermer automatiquement au bout de 3 secondes.

---

## ⚠️ Avertissements
- **Comportement des API Internes :** Puisque l'application utilise la réflexion pour accéder à des API cachées, son comportement n'est garanti que sur les anciennes versions d'Android (API 17). Sur les versions beaucoup plus récentes (Android 9/10+), Google a strictement restreint l'utilisation de la réflexion (Non-SDK interfaces), ce qui peut empêcher cette méthode de fonctionner.

---
*Créé avec passion pour redonner un coup de neuf aux anciens appareils Android.*
