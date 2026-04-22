import auth from '../utils/auth.js';

export function initSidebar() {
    const sidebarHtml = `
        <div id="sidebar">
            <h2 style="margin-bottom: 2rem; text-align: center;">StudentTrack</h2>
            <nav>
                <a href="dashboard.html" class="nav-link" id="nav-dashboard">Dashboard</a>
                <a href="attendance.html" class="nav-link" id="nav-attendance">Attendance</a>
                <a href="tasks.html" class="nav-link" id="nav-tasks">Tasks</a>
                <a href="assignments.html" class="nav-link" id="nav-assignments">Assignments</a>
                <a href="library.html" class="nav-link" id="nav-library">Library</a>
                <a href="game.html" class="nav-link" id="nav-game">Mini Game</a>
                <a href="#" class="nav-link" id="logout-btn" style="margin-top: 2rem; color: var(--accent-red);">Logout</a>
            </nav>
        </div>
    `;

    document.body.insertAdjacentHTML('afterbegin', sidebarHtml);

    // Set active link
    const path = window.location.pathname;
    const page = path.split("/").pop();
    const activeLink = document.querySelector(`a[href="${page}"]`);
    if (activeLink) activeLink.classList.add('active');

    // Logout logic
    document.getElementById('logout-btn').addEventListener('click', (e) => {
        e.preventDefault();
        auth.logout();
    });
}
