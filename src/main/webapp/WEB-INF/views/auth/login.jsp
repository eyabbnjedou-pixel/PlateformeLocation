<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion - SmartSpace System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    
    <style>
        :root {
            --primary-color: #ec30ed;
            --primary-dark: #5B21B6;
            --primary-light: #A78BFA;
            --accent-color: #F59E0B;
            --accent-light: #FCD34D;
            --dark-color: #c10fb4;
            --text-muted: #6B7280;
            --bg-light: #F8FAFC;
            --white: #FFFFFF;
            --shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.25);
            --shadow-sm: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: var(--bg-light);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }

        .login-container {
            display: flex;
            max-width: 1000px;
            width: 100%;
            background: var(--white);
            border-radius: 24px;
            overflow: hidden;
            box-shadow: var(--shadow);
        }

        /* ===== LEFT SIDE - IMAGE/BRAND SECTION ===== */
        .login-image-section {
            flex: 1;
            background: #ae85d6;
            background-size: 600% 600%;
            animation: gradientMove 10s ease infinite;
            
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 60px 40px;
            position: relative;
            overflow: hidden;
        }

        .login-image-section::before {
           display: none;
        }
        @keyframes gradientMove {
    0% { background-position: 0% 50%; }
    50% { background-position: 100% 50%; }
    100% { background-position: 0% 50%; }
}

        @keyframes pulse {
            0%, 100% { transform: scale(1); opacity: 0.5; }
            50% { transform: scale(1.1); opacity: 0.8; }
        }

        /* Decorative shapes */
        .shape {
            position: absolute;
            border-radius: 50%;
            opacity: 0.35; /* أخف annimation des bulle*/
            animation: float 9s ease-in-out infinite;
        }
        @keyframes float {
    0% { transform: translateY(0px); }
    50% { transform: translateY(-20px); }
    100% { transform: translateY(0px); }
}

        .shape-1 {
            width: 200px;
            height: 200px;
            background: #A78BFA;
            top: -50px;
            right: -50px;
            animation-delay: 0s;
        }

        .shape-2 {
            width: 150px;
            height: 150px;
            background: #8B5CF6;
            bottom: 50px;
            left: -30px;
            animation-delay: 2s;
        }

        .shape-3 {
            width: 80px;
            height: 80px;
            background: #C4B5FD;
            bottom: 150px;
            right: 30px;
            animation-delay: 4s;
        }

        .brand-content {
            position: relative;
            z-index: 2;
            text-align: center;
            color: var(--white);
        }

        .brand-logo {
            width: 80px;
            height: 80px;
            background: var(--white);
            border-radius: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 24px;
            box-shadow: var(--shadow-sm);
        }

        .brand-logo i {
            font-size: 36px;
            background: linear-gradient(135deg, var(--primary-color), var(--accent-color));
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
        }

        .brand-name {
            font-size: 32px;
            font-weight: 700;
            margin-bottom: 8px;
            letter-spacing: -0.5px;
        }

        .brand-name span {
            color: var(--accent-light);
        }

        .brand-tagline {
            font-size: 16px;
            opacity: 0.9;
            font-weight: 300;
            margin-bottom: 40px;
        }

        /* Image placeholder */
        .image-placeholder {
            width: 100%;
            max-width: 350px;
            height: auto;
            background: transparent;
            border-radius: 16px;
            border: none;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            backdrop-filter: blur(10px);
            margin-top: 20px;
        }

        .image-placeholder i {
            font-size: 48px;
            opacity: 0.7;
            margin-bottom: 12px;
        }

        .image-placeholder p {
            font-size: 14px;
            opacity: 0.7;
        }

        
        .actual-image {
    width: 100%;
    height: 300px;   
    object-fit: cover; 
    border-radius: 20px;
    box-shadow: var(--shadow);
    margin-top: 20px;
}

        /* Feature list */
        .features-list {
            list-style: none;
            margin-top: 40px;
            text-align: left;
        }

        .features-list li {
            display: flex;
            align-items: center;
            gap: 12px;
            margin-bottom: 16px;
            font-size: 14px;
            opacity: 0.9;
        }

        .features-list li i {
            width: 24px;
            height: 24px;
            background: rgba(255,255,255,0.2);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 12px;
        }

        /* ===== RIGHT SIDE - FORM SECTION ===== */
        .login-form-section {
            flex: 1;
            padding: 60px 50px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        .form-header {
            margin-bottom: 40px;
        }

        .form-header h1 {
            font-size: 28px;
            font-weight: 700;
            color: var(--dark-color);
            margin-bottom: 8px;
        }

        .form-header p {
            color: var(--text-muted);
            font-size: 15px;
        }

        .form-group {
            margin-bottom: 24px;
        }

        .form-group label {
            display: block;
            font-size: 14px;
            font-weight: 500;
            color: var(--dark-color);
            margin-bottom: 8px;
        }

        .input-wrapper {
            position: relative;
        }

        .input-wrapper i {
            position: absolute;
            left: 16px;
            top: 50%;
            transform: translateY(-50%);
            color: var(--text-muted);
            font-size: 18px;
            transition: color 0.3s ease;
        }

        .form-control {
            width: 100%;
            padding: 14px 16px 14px 48px;
            border: 2px solid #E5E7EB;
            border-radius: 12px;
            font-size: 15px;
            font-family: 'Poppins', sans-serif;
            transition: all 0.3s ease;
            background: var(--bg-light);
        }

        .form-control:focus {
            outline: none;
            border-color: var(--primary-color);
            background: var(--white);
            box-shadow: 0 0 0 4px rgba(124, 58, 237, 0.2);
        }

        .form-control:focus + i,
        .input-wrapper:focus-within i {
            color: var(--primary-color);
        }

        .form-control::placeholder {
            color: #9CA3AF;
        }

        .form-options {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 24px;
            font-size: 14px;
        }

        .remember-me {
            display: flex;
            align-items: center;
            gap: 8px;
            cursor: pointer;
        }

        .remember-me input[type="checkbox"] {
            width: 18px;
            height: 18px;
            accent-color: var(--primary-color);
            cursor: pointer;
        }

        .remember-me span {
            color: var(--text-muted);
        }

        .forgot-password {
            color: var(--primary-color);
            text-decoration: none;
            font-weight: 500;
            transition: color 0.3s ease;
        }

        .forgot-password:hover {
            color: var(--primary-dark);
        }

        .btn-login {
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, var(--primary-color) 0%, var(--primary-dark) 100%);
            border: none;
            border-radius: 12px;
            color: var(--white);
            font-size: 16px;
            font-weight: 600;
            font-family: 'Poppins', sans-serif;
            cursor: pointer;
            transition: all 0.3s ease;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
        }

        .btn-login:hover {
            transform: translateY(-2px);
            box-shadow: 0 10px 20px rgba(124, 58, 237, 0.4);
        }

        .btn-login:active {
            transform: translateY(0);
        }

        .btn-login i {
            transition: transform 0.3s ease;
        }

        .btn-login:hover i {
            transform: translateX(4px);
        }

        /* Alert styling */
        .alert-error {
            background: linear-gradient(135deg, #FEE2E2 0%, #FECACA 100%);
            border: none;
            border-left: 4px solid #EF4444;
            border-radius: 12px;
            padding: 16px 20px;
            margin-bottom: 24px;
            display: flex;
            align-items: center;
            gap: 12px;
            color: #991B1B;
            font-size: 14px;
        }

        .alert-error i {
            font-size: 20px;
            color: #EF4444;
        }

        /* Divider */
        .divider {
            display: flex;
            align-items: center;
            margin: 32px 0;
            color: var(--text-muted);
            font-size: 14px;
        }

        .divider::before,
        .divider::after {
            content: '';
            flex: 1;
            height: 1px;
            background: #E5E7EB;
        }

        .divider span {
            padding: 0 16px;
        }

        /* Social login */
        .social-login {
            display: flex;
            gap: 12px;
        }

        .btn-social {
            flex: 1;
            padding: 12px;
            border: 2px solid #E5E7EB;
            border-radius: 12px;
            background: var(--white);
            cursor: pointer;
            transition: all 0.3s ease;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
            font-size: 14px;
            font-weight: 500;
            color: var(--dark-color);
            font-family: 'Poppins', sans-serif;
        }

        .btn-social:hover {
            border-color: var(--primary-color);
            background: var(--bg-light);
        }

        .btn-social i {
            font-size: 20px;
        }

        .btn-social.google i { color: #EA4335; }
        .btn-social.microsoft i { color: #00A4EF; }

        /* Footer */
        .form-footer {
            text-align: center;
            margin-top: 32px;
            color: var(--text-muted);
            font-size: 14px;
        }

        .form-footer a {
            color: var(--primary-color);
            text-decoration: none;
            font-weight: 600;
        }

        .form-footer a:hover {
            text-decoration: underline;
        }

        /* ===== RESPONSIVE ===== */
        @media (max-width: 768px) {
            .login-container {
                flex-direction: column;
                max-width: 450px;
            }

            .login-image-section {
    flex: 1;
    background: linear-gradient(270deg, #7C3AED, #8B5CF6, #5B21B6);
    background-size: 600% 600%;
    animation: gradientMove 10s ease infinite;

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 60px 40px;
    position: relative;
    overflow: hidden;
}

            .image-placeholder {
                display: none;
            }

            .features-list {
                display: none;
            }

            .login-form-section {
                padding: 40px 30px;
            }

            .form-options {
                flex-direction: column;
                gap: 12px;
                align-items: flex-start;
            }
        }
    </style>
</head>
<body>

<div class="login-container">
    <!-- ===== LEFT SIDE - IMAGE & BRANDING ===== -->
    <div class="login-image-section">
        <!-- Decorative shapes -->
        <div class="shape shape-1"></div>
        <div class="shape shape-2"></div>
        <div class="shape shape-3"></div>

        <div class="brand-content">
            <!-- Logo removed -->
            
            <!-- Brand Name -->
            <h2 class="brand-name">SmartSpace <span>System</span></h2>
            <p class="brand-tagline">Votre espace intelligent de réservation</p>

            <!-- Image Placeholder - Replace with your actual image -->
            <!--<div class="image-placeholder">
               <!-- <img src="${pageContext.request.contextPath}/PlateformeLocation/src/main/webapp/assets/images/meeting-room.jpg" alt="Meeting Room" class="actual-image"> -->
            

            <!-- OR use an actual image like this: -->
            <div class="image-placeholder">
             <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQe_Y2e04SDzwlqy6PUICZG-3-PJnzktt4oQQ&s" alt="meeting-room.jpg" class="actual-image"><br>
              -->
            </div>

            <!-- Features List -->
            <ul class="features-list">
                <li>
                    <i class="fas fa-check"></i>
                    <span>Réservation instantanée</span>
                </li>
                <li>
                    <i class="fas fa-check"></i>
                    <span>Gestion optimisée</span>
                </li>
                <li>
                    <i class="fas fa-check"></i>
                    <span>Alertes intelligentes</span>
                </li>
            </ul>
        </div>
    </div>
  
    <!-- ===== RIGHT SIDE - LOGIN FORM ===== -->
    <div class="login-form-section">
    <div class="form-header">
        <h1>Vous êtes le Bienvenue ! 👋</h1>
        <p>Connectez-vous pour accéder à votre espace</p>
    </div>

    <!-- Notification Banner (Hidden by default) -->
    <div id="notification-banner" class="notification-banner" style="display: none;">
        <i class="fas fa-check-circle"></i>
        <span>Un mail a été envoyé avec un nouveau mot de passe</span>
        <button type="button" class="close-btn" onclick="closeBanner()">
            <i class="fas fa-times"></i>
        </button>
    </div>

    <!-- Error Alert -->
    <c:if test="${not empty error}">
        <div class="alert-error">
            <i class="fas fa-exclamation-circle"></i>
            <span>${error}</span>
        </div>
    </c:if>

    <!-- Login Form -->
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label for="login">Identifiant</label>
            <div class="input-wrapper">
                <input type="text" 
                       class="form-control" 
                       id="login" 
                       name="login" 
                       placeholder="Entrez votre identifiant"
                       required 
                       autofocus>
                <i class="fas fa-user"></i>
            </div>
        </div>

        <div class="form-group">
            <label for="motDePasse">Mot de passe</label>
            <div class="input-wrapper">
                <input type="password" 
                       class="form-control" 
                       id="motDePasse" 
                       name="motDePasse" 
                       placeholder="Entrez votre mot de passe"
                       required>
                <i class="fas fa-lock"></i>
            </div>
        </div>

        <div class="form-options">
            <label class="remember-me">
                <input type="checkbox" name="remember">
                <span>Se souvenir de moi</span>
            </label>
            <!-- ✅ Updated: Shows notification on click -->
            <a href="#" class="forgot-password" onclick="showForgotPasswordBanner(event)">
                Mot de passe oublié ?
            </a>
        </div>

        <button type="submit" class="btn-login">
            Se connecter
            <i class="fas fa-arrow-right"></i>
        </button>
    </form>
    <script>
function showForgotPasswordBanner(event) {
    event.preventDefault();
    
    const banner = document.getElementById('notification-banner');
    banner.style.display = 'flex';
    
    // Auto-hide after 5 seconds
    setTimeout(() => {
        closeBanner();
    }, 5000);
}

function closeBanner() {
    const banner = document.getElementById('notification-banner');
    banner.style.opacity = '0';
    banner.style.transform = 'translateY(-10px)';
    
    setTimeout(() => {
        banner.style.display = 'none';
        banner.style.opacity = '1';
        banner.style.transform = 'translateY(0)';
    }, 300);
}
</script>


    

    <!-- Footer -->
    <p class="form-footer">
        Pas encore de compte ? 
        <a href="${pageContext.request.contextPath}/register" target="_blank">
         Créer un Compte
        </a>
        <br>
        <!-- ✅ Updated: Links to LinkedIn -->
        <a href="https://www.linkedin.com/in/aya-benjeddou" target="_blank">
            Contactez l'administrateur
        </a>
    </p>
</div>

   </div>    

</body>
</html>