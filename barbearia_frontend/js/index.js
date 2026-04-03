// const modal = document.getElementById('bookingModal');
//         const toast = document.getElementById('toast');

//         function openModal() {
//             modal.classList.remove('hidden');
//             document.body.style.overflow = 'hidden';
//         }

//         function closeModal() {
//             modal.classList.add('hidden');
//             document.body.style.overflow = 'auto';
//         }

//         function handleSubmit(event) {
//             event.preventDefault();
            
//             // Simulação de envio
//             closeModal();
            
//             // Mostrar Toast
//             toast.classList.remove('translate-y-24');
            
//             setTimeout(() => {
//                 toast.classList.add('translate-y-24');
//             }, 3000);

//             // Reseta o formulário
//             event.target.reset();
//         }

//         // Fechar modal com a tecla ESC
//         window.addEventListener('keydown', (e) => {
//             if (e.key === 'Escape' && !modal.classList.contains('hidden')) {
//                 closeModal();
//             }
//         });

  // Funções de Modal
        function openModal(id) {
            document.getElementById(id).classList.remove('hidden');
            document.body.style.overflow = 'hidden';
        }

        function closeModal(id) {
            document.getElementById(id).classList.add('hidden');
            document.body.style.overflow = 'auto';
        }

        // Troca entre Site e Admin
         function toggleAdminView() {
            const main = document.getElementById('mainContent');
            const admin = document.getElementById('adminView');
            
            if (admin.classList.contains('hidden')) {
                main.classList.add('hidden');
                admin.classList.remove('hidden');
                window.scrollTo(0, 0);
            } else {
                main.classList.remove('hidden');
                admin.classList.add('hidden');
            }
        }

        // Troca abas de Auth
        function switchAuthTab(type) {
            const loginForm = document.getElementById('loginForm');
            const signupForm = document.getElementById('signupForm');
            const tabLogin = document.getElementById('tabLogin');
            const tabSignup = document.getElementById('tabSignup');

            if (type === 'login') {
                loginForm.classList.remove('hidden');
                signupForm.classList.add('hidden');
                tabLogin.className = 'flex-1 py-3 text-amber-500 border-b-2 border-amber-500 font-bold';
                tabSignup.className = 'flex-1 py-3 text-slate-500 hover:text-white transition-colors font-bold';
            } else {
                loginForm.classList.add('hidden');
                signupForm.classList.remove('hidden');
                tabSignup.className = 'flex-1 py-3 text-amber-500 border-b-2 border-amber-500 font-bold';
                tabLogin.className = 'flex-1 py-3 text-slate-500 hover:text-white transition-colors font-bold';
            }
        }

        // Autenticação Simulada
        function handleAuth(event, type) {
            event.preventDefault();
            const authContainer = document.getElementById('authContainer');
            const adminDashboard = document.getElementById('adminDashboard');

            // Simula um loading
            event.submitter.innerHTML = '<i class="fas fa-spinner animate-spin"></i> Processando...';
            
            setTimeout(() => {
                authContainer.classList.add('hidden');
                adminDashboard.classList.remove('hidden');
                showToast(type === 'login' ? "Bem-vindo de volta!" : "Conta criada com sucesso!");
            }, 1000);
        }

        function logout() {
            document.getElementById('authContainer').classList.remove('hidden');
            document.getElementById('adminDashboard').classList.add('hidden');
            showToast("Você saiu do painel.");
        }

        // Feedback
        function showToast(message) {
            const toast = document.getElementById('toast');
            document.getElementById('toastMessage').innerText = message;
            toast.classList.remove('translate-y-24');
            setTimeout(() => toast.classList.add('translate-y-24'), 3000);
        }

        function handleSubmit(event) {
            event.preventDefault();
            closeModal('bookingModal');
            showToast("Agendamento enviado!");
            event.target.reset();
        }

        
//FUNÇÃO VERIFICAR HORARIO
function checkBarbeariaOpen() {
    const data = new Date();
    
    const dia = data.getDay(); // 0 = Domingo, 1 = Segunda ...
    const hora = data.getHours();
    const minutos = data.getMinutes();

    const horarioAtual = hora * 60 + minutos;
    const abertura = 9 * 60;   // 09:00
    const fechamento = 20 * 60; // 20:00

    const diaValido = dia >= 1 && dia <= 6; // Segunda a sábado

    return diaValido && horarioAtual >= abertura && horarioAtual < fechamento;
}

const spanItem = document.getElementById("date-span");
const isOpen = checkBarbeariaOpen();

if (isOpen) {
    spanItem.classList.remove("bg-red-500");
    spanItem.classList.add("bg-green-600");
    spanItem.innerText = "🟢 Aberto agora (09h às 20h)";
} else {
    spanItem.classList.remove("bg-green-600");
    spanItem.classList.add("bg-red-500");
    spanItem.innerText = "🔴 Fechado agora";
}




const menuBtn = document.getElementById("menu-btn");
const mobileMenu = document.getElementById("mobile-menu");

menuBtn.addEventListener("click", () => {
    mobileMenu.classList.toggle("hidden");
}); 