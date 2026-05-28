document.addEventListener('DOMContentLoaded', () => {
    
    document.querySelectorAll('.nav-menu a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();

            const targetId = this.getAttribute('href');
            const targetElement = document.querySelector(targetId);

            if (targetElement) {
                const headerHeight = 70;
                const elementPosition = targetElement.getBoundingClientRect().top;
                const offsetPosition = elementPosition + window.pageYOffset - headerHeight;

                window.scrollTo({
                    top: offsetPosition,
                    behavior: 'smooth'
                });

                document.querySelectorAll('.nav-menu a').forEach(navLink => {
                    navLink.classList.remove('active');
                });
                this.classList.add('active');
            }
        });
    });

    const searchInput = document.querySelector('.search-box input');
    const searchForm = document.querySelector('.search-box');
    const bookCards = document.querySelectorAll('.book-card');

    if (searchInput) {
        searchInput.addEventListener('input', (e) => {
            const searchTerm = e.target.value.toLowerCase().trim();

            bookCards.forEach(card => {
                const title = card.querySelector('.book-title').textContent.toLowerCase();
                const author = card.querySelector('.book-author').textContent.toLowerCase();
                const genre = card.querySelector('.book-genre').textContent.toLowerCase();

                if (title.includes(searchTerm) || author.includes(searchTerm) || genre.includes(searchTerm)) {
                    card.classList.remove('book-card-hidden');
                } else {
                    card.classList.add('book-card-hidden');
                }
            });
        });

        searchForm.addEventListener('submit', (e) => {
            e.preventDefault();
        });
    }

    const donationForm = document.querySelector('.donation-form');
    
    if (donationForm) {
        donationForm.addEventListener('submit', (e) => {
            e.preventDefault();
            
            const email = document.getElementById('userEmail').value;
            
            alert(`Obrigado! A sua proposta de doação foi enviada com sucesso.\nOs administradores da Vulture Books entrarão em contacto através do e-mail: ${email}`);
            
            donationForm.reset();
        });
    }
});