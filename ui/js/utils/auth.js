const auth = {
    setToken: (token) => localStorage.setItem('token', token),
    getToken: () => localStorage.getItem('token'),
    setUser: (user) => localStorage.setItem('user', JSON.stringify(user)),
    getUser: () => JSON.parse(localStorage.getItem('user')),
    logout: () => {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        window.location.href = '/ui/pages/index.html';
    },
    isAuthenticated: () => !!localStorage.getItem('token'),
    checkAuth: () => {
        if (!auth.isAuthenticated()) {
            window.location.href = '/ui/pages/index.html';
        }
    }
};

export default auth;
