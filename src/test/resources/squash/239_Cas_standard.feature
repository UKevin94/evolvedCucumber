# Automation priority: null
# Automation status: TRANSMITTED
# Test case importance: LOW
# language: fr

Fonctionnalité: Test des fonctionnalité de Gestion utilisateur

  Contexte:
    Étant donné que je suis sur la page de jacksonviews
    Et que je suis connecté en tant qu'admin

    Scénario: Je veux changer le mail de l'administrateur
      Soit je vais sur la page User Management
      Lorsque je clique sur le bouton Edit de l'administrateur
      Et que je modifie son mail et valide
      Alors le mail est modifié

    Plan du scénario: Je souhaite vérifier les rôles des utilisateurs
      Etant donné que je suis sur la page UserManagement
      Quand je récupère les profils de l'utilisateur avec le login <login>
      Alors je peux voir le profil User et Admin

      Exemples:
      | login |
      | "system" |
      | "admin"  |
      | "user"   |