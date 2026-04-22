const BASE_URL = 'http://localhost:8080/api';

async function request(endpoint, options = {}) {
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers,
    };

    const config = {
        ...options,
        headers,
    };

    try {
        const response = await fetch(`${BASE_URL}${endpoint}`, config);
        
        if (response.status === 401) {
            // Unauthorized - clear user and redirect to login
            localStorage.removeItem('user');
            window.location.href = '/ui/pages/index.html';
            return;
        }

        if (!response.ok) {
            const error = await response.text();
            throw new Error(error || 'Something went wrong');
        }

        // Check if response is empty
        const contentType = response.headers.get('content-type');
        if (contentType && contentType.includes('application/json')) {
            return await response.json();
        }
        return await response.text();

    } catch (error) {
        console.error('API Request Error:', error);
        throw error;
    }
}

const api = {
    get: (endpoint) => request(endpoint, { method: 'GET' }),
    post: (endpoint, body) => request(endpoint, { method: 'POST', body: JSON.stringify(body) }),
    put: (endpoint, body) => request(endpoint, { method: 'PUT', body: JSON.stringify(body) }),
    delete: (endpoint) => request(endpoint, { method: 'DELETE' }),
};

export default api;
