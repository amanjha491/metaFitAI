import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 500,
    duration: '1m',
    thresholds: {
        http_req_duration: ['p(95)<200'],
    },
};

// URL for the Gateway
const BASE_URL = 'http://localhost:8080/metaFitAi/users';

export default function () {
    // We use a fixed set of IDs to simulate a high cache hit rate
    const ids = ['user1', 'user2', 'user3', 'user4', 'user5'];
    const userId = ids[Math.floor(Math.random() * ids.length)];
    
    const res = http.get(`${BASE_URL}/${userId}`);
    
    check(res, {
        'is status 200': (r) => r.status === 200,
    });
    
    sleep(0.1);
}
