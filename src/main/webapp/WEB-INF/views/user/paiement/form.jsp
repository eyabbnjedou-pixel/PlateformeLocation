<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%-- Formulaire de paiement --%>
        <%@taglib prefix="c" uri="jakarta.tags.core" %>
            <%@taglib prefix="fn" uri="jakarta.tags.functions" %>

                <c:set var="pageTitle" value="Paiement Sécurisé - SmartSpace System" scope="request" />
                <jsp:include page="/WEB-INF/views/includes/header.jsp" />

                <style>
                    .payment-container {
                        max-width: 900px;
                        margin: 40px auto;
                        display: grid;
                        grid-template-columns: 1fr 1fr;
                        gap: 30px;
                        background: var(--white);
                        border-radius: var(--radius-lg);
                        box-shadow: 0 15px 35px rgba(0, 0, 0, 0.05);
                        overflow: hidden;
                    }

                    /* Left Side: Summary */
                    .payment-summary {
                        background: linear-gradient(135deg, var(--primary) 0%, var(--primary-dark) 100%);
                        color: var(--white);
                        padding: 40px;
                    }

                    .summary-title {
                        font-size: 24px;
                        font-weight: 700;
                        margin-bottom: 30px;
                        display: flex;
                        align-items: center;
                        gap: 10px;
                    }

                    .summary-item {
                        display: flex;
                        justify-content: space-between;
                        margin-bottom: 15px;
                        padding-bottom: 15px;
                        border-bottom: 1px solid rgba(255, 255, 255, 0.2);
                    }

                    .summary-item:last-child {
                        border-bottom: none;
                        margin-bottom: 0;
                        padding-bottom: 0;
                    }

                    .summary-label {
                        opacity: 0.8;
                        font-size: 14px;
                    }

                    .summary-value {
                        font-weight: 600;
                        font-size: 16px;
                    }

                    .total-price {
                        font-size: 32px;
                        font-weight: 700;
                        margin-top: 30px;
                        padding-top: 20px;
                        border-top: 2px solid rgba(255, 255, 255, 0.3);
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                    }

                    /* Right Side: Form */
                    .payment-form-section {
                        padding: 40px;
                    }

                    .form-title {
                        font-size: 20px;
                        font-weight: 600;
                        color: var(--dark);
                        margin-bottom: 24px;
                    }

                    .form-group {
                        margin-bottom: 20px;
                    }

                    .form-label {
                        display: block;
                        font-size: 13px;
                        font-weight: 600;
                        color: var(--gray-700);
                        margin-bottom: 8px;
                    }

                    .form-control {
                        width: 100%;
                        padding: 12px 16px;
                        border: 1px solid var(--gray-300);
                        border-radius: var(--radius-sm);
                        font-family: inherit;
                        font-size: 14px;
                        transition: var(--transition);
                    }

                    .form-control:focus {
                        border-color: var(--primary);
                        box-shadow: 0 0 0 3px rgba(79, 70, 229, 0.1);
                        outline: none;
                    }

                    .row-2 {
                        display: grid;
                        grid-template-columns: 1fr 1fr;
                        gap: 20px;
                    }

                    .btn-submit-payment {
                        width: 100%;
                        padding: 14px;
                        background: #10B981;
                        color: var(--white);
                        border: none;
                        border-radius: var(--radius-sm);
                        font-size: 16px;
                        font-weight: 600;
                        cursor: pointer;
                        transition: var(--transition);
                        margin-top: 10px;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        gap: 10px;
                    }

                    .btn-submit-payment:hover {
                        background: #059669;
                        transform: translateY(-2px);
                        box-shadow: 0 4px 12px rgba(16, 185, 129, 0.3);
                    }

                    .secure-badge {
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        gap: 8px;
                        margin-top: 20px;
                        color: var(--gray-500);
                        font-size: 12px;
                    }

                    @media (max-width: 768px) {
                        .payment-container {
                            grid-template-columns: 1fr;
                        }
                    }

                    /* New Stripe-like Form CSS */
                    .section-title {
                        font-size: 16px;
                        font-weight: 600;
                        margin-bottom: 16px;
                        margin-top: 24px;
                        color: #1a1a1a;
                    }

                    .section-title:first-child {
                        margin-top: 0;
                    }

                    .form-group-stripe {
                        margin-bottom: 16px;
                    }

                    .form-label-stripe {
                        display: block;
                        font-size: 13px;
                        font-weight: 500;
                        color: #4a4a4a;
                        margin-bottom: 8px;
                    }

                    .form-control-stripe {
                        width: 100%;
                        padding: 10px 12px;
                        border: 1px solid #e0e0e0;
                        border-radius: 6px;
                        font-size: 14px;
                        transition: border-color 0.2s, box-shadow 0.2s;
                    }

                    .form-control-stripe:focus {
                        border-color: #0070ba;
                        box-shadow: 0 0 0 3px rgba(0, 112, 186, 0.1);
                        outline: none;
                    }

                    .card-container {
                        border: 1px solid #e0e0e0;
                        border-radius: 8px;
                        padding: 20px;
                        background: #fff;
                    }

                    .card-header {
                        display: flex;
                        align-items: center;
                        gap: 8px;
                        font-weight: 600;
                        margin-bottom: 16px;
                    }

                    .card-icons {
                        display: flex;
                        gap: 4px;
                        float: right;
                    }

                    .card-icons i {
                        font-size: 24px;
                    }

                    .card-wrapper {
                        border: 1px solid #e0e0e0;
                        border-radius: 6px;
                        margin-bottom: 16px;
                        overflow: hidden;
                    }

                    .card-wrapper input {
                        border: none;
                        box-shadow: none;
                        border-radius: 0;
                        padding: 12px;
                    }

                    .card-wrapper input:focus {
                        background: #fcfcfc;
                    }

                    .card-wrapper .top-row {
                        border-bottom: 1px solid #e0e0e0;
                        display: flex;
                        align-items: center;
                        padding-right: 12px;
                        background: #fff;
                    }

                    .card-wrapper .top-row input {
                        flex: 1;
                    }

                    .card-wrapper .bottom-row {
                        display: flex;
                    }

                    .card-wrapper .bottom-row input {
                        flex: 1;
                    }

                    .card-wrapper .bottom-row input:first-child {
                        border-right: 1px solid #e0e0e0;
                    }

                    .cvv-input {
                        background-image: url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='%23999' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'><rect x='2' y='5' width='20' height='14' rx='2' ry='2'></rect><line x1='2' y1='10' x2='22' y2='10'></line></svg>");
                        background-repeat: no-repeat;
                        background-position: 95% center;
                        background-size: 20px;
                    }

                    .save-info-box {
                        display: flex;
                        gap: 12px;
                        margin-top: 24px;
                        border: 1px solid #e0e0e0;
                        padding: 16px;
                        border-radius: 6px;
                    }

                    .save-info-box input[type="checkbox"] {
                        margin-top: 4px;
                    }

                    .save-info-text strong {
                        display: block;
                        font-size: 14px;
                        color: #333;
                        margin-bottom: 4px;
                    }

                    .save-info-text p {
                        font-size: 13px;
                        color: #666;
                        margin: 0;
                    }

                    .btn-pay-stripe {
                        background: #0070ba;
                        color: white;
                        width: 100%;
                        padding: 14px;
                        border: none;
                        border-radius: 6px;
                        font-size: 16px;
                        font-weight: 600;
                        margin-top: 24px;
                        cursor: pointer;
                        transition: background 0.2s;
                    }

                    .btn-pay-stripe:hover {
                        background: #005ea6;
                    }
                </style>

                <div class="payment-container">
                    <!-- Left: Summary -->
                    <div class="payment-summary">
                        <h2 class="summary-title">
                            <i class="fas fa-receipt"></i> Résumé de la réservation
                        </h2>

                        <div class="summary-item">
                            <span class="summary-label">Espace</span>
                            <span class="summary-value">${reservation.bien.nom}</span>
                        </div>
                        <div class="summary-item">
                            <span class="summary-label">Date d'arrivée</span>
                            <span class="summary-value">${fn:replace(reservation.dateHeureDebut, 'T', ' ')}</span>
                        </div>
                        <div class="summary-item">
                            <span class="summary-label">Date de départ</span>
                            <span class="summary-value">${fn:replace(reservation.dateHeureFin, 'T', ' ')}</span>
                        </div>
                        <div class="summary-item">
                            <span class="summary-label">Client</span>
                            <span class="summary-value">${reservation.utilisateur.nomComplet}</span>
                        </div>

                        <div class="total-price">
                            <span style="font-size: 16px; opacity: 0.8; font-weight: normal;">Total à payer</span>
                            <span>
                                <c:choose>
                                    <c:when test="${reservation.montantTotal > 0}">
                                        ${reservation.montantTotal} TND
                                    </c:when>
                                    <c:otherwise>
                                        150.0 TND <!-- Valeur par défaut simulée -->
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                    </div>

                    <!-- Right: Payment Form -->
                    <div class="payment-form-section" style="padding: 30px;">
                        <form action="${pageContext.request.contextPath}/user/paiement?id=${reservation.id}"
                            method="POST">

                            <h3 class="section-title">Informations de contact</h3>

                            <div class="form-group-stripe">
                                <label class="form-label-stripe">E-mail</label>
                                <input type="email" class="form-control-stripe" name="email"
                                    placeholder="email@exemple.com" value="${userSession.login}" required>
                            </div>

                            <h3 class="section-title">Mode de paiement</h3>

                            <div class="card-container">
                                <div class="card-header">
                                    <i class="fas fa-credit-card"></i> Carte
                                </div>

                                <label class="form-label-stripe">Informations de la carte</label>
                                <div class="card-wrapper">
                                    <div class="top-row">
                                        <input type="text" class="form-control-stripe" name="cardNumber"
                                            placeholder="1234 1234 1234 1234" maxlength="19" required>
                                        <div class="card-icons">
                                            <i class="fab fa-cc-visa" style="color: #1A1F71;"></i>
                                            <i class="fab fa-cc-mastercard" style="color: #EB001B;"></i>
                                            <i class="fab fa-cc-amex" style="color: #2E77BC;"></i>
                                        </div>
                                    </div>
                                    <div class="bottom-row">
                                        <input type="text" class="form-control-stripe" name="cardExpiry"
                                            placeholder="MM / AA" maxlength="7" required>
                                        <input type="text" class="form-control-stripe cvv-input" name="cardCvv"
                                            placeholder="CVC" maxlength="4" required>
                                    </div>
                                </div>

                                <div class="form-group-stripe">
                                    <label class="form-label-stripe">Nom du titulaire</label>
                                    <input type="text" class="form-control-stripe" name="cardName"
                                        placeholder="Nom complet sur la carte" required>
                                </div>

                                <div class="form-group-stripe">
                                    <label class="form-label-stripe">Pays ou région</label>
                                    <select class="form-control-stripe" name="country">
                                        <option value="TN">Tunisie</option>
                                        <option value="FR">France</option>
                                        <option value="CA">Canada</option>
                                    </select>
                                </div>
                            </div>

                            <div class="save-info-box">
                                <input type="checkbox" id="saveInfo" name="saveInfo">
                                <div class="save-info-text">
                                    <label for="saveInfo"><strong>Enregistrer mes informations pour un paiement plus
                                            rapide</strong></label>
                                    <p>Payez en toute sécurité avec codeleger et partout où Link est accepté.</p>
                                </div>
                            </div>

                            <button type="submit" class="btn-pay-stripe">
                                Payer
                            </button>
                        </form>
                    </div>
                </div>

                <jsp:include page="/WEB-INF/views/includes/footer.jsp" />