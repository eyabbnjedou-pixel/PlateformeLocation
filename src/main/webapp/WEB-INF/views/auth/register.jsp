<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Inscription - SmartSpace</title>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
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
        .recaptcha-container {
    display: flex;
    justify-content: center;
    margin: 20px 0;
}

/* Responsive (تصغير في الهاتف) */
@media (max-width: 400px) {
    .g-recaptcha {
        transform: scale(0.85);
        transform-origin: center;
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

    <!-- RIGHT -->
    <div class="login-form-section">

    <div class="form-header">
        <h1>Vous êtes le Bienvenue ! 👋</h1>
        <p>Inscrivez-vous pour accéder à votre espace</p>
    </div>

    <c:if test="${not empty error}">
        <div class="alert-error">
            <i class="fas fa-exclamation-circle"></i>
            ${error}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/register" method="post">

        <!-- Nom complet -->
        <div class="form-group">
            <label>Nom complet</label>
            <div class="input-wrapper">
                <i class="fas fa-user"></i>
                <input type="text" name="nomComplet" class="form-control" placeholder="Entrer votre nom complet" required>
            </div>
        </div>

        <!-- Login -->
        <div class="form-group">
            <label>Identifiant</label>
            <div class="input-wrapper">
                <i class="fas fa-id-badge"></i>
                <input type="text" name="login" class="form-control" placeholder="Entrer votre login" required>
            </div>
        </div>

        <!-- Mot de passe -->
        <div class="form-group">
            <label>Mot de passe</label>
            <div class="input-wrapper">
                <i class="fas fa-lock"></i>
                <input type="password" name="motDePasse" class="form-control" placeholder="Entrer votre mot de passe" required>
            </div>
        </div>

        <!-- ROLE (Hidden, default to USER) -->
        <input type="hidden" name="role" value="USER">
	<!--  reCAPTCHA  -->
<div class="form-group" style="text-align:center;">
    <div class="g-recaptcha" data-sitekey="6Lc5g7wsAAAAAALKn-7EMrNh33SFx5x7h-mOhLf3"></div>
</div>
        <!-- BUTTON -->
        <button type="submit" class="btn-login">
            S'inscrire <i class="fas fa-arrow-right"></i>
        </button>

    </form>

    <div class="form-footer">
        Déjà inscrit ?
        <a href="${pageContext.request.contextPath}/login">Se connecter</a>
        <br>
        <a href="https://www.linkedin.com/in/aya-benjeddou" target="_blank">
            Contactez l'administrateur
        </a>
    </div>

</div>
   
</div>
<script>
document.querySelector("form").addEventListener("submit", function(e) {
    var response = grecaptcha.getResponse();
    if(response.length === 0) {
        e.preventDefault();
        alert("Veuillez vérifier le reCAPTCHA !");
    }
});
</script>
</body>
</html>