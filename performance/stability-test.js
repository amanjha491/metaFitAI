import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 1000 }, // ramp-up to 1000 users
    { duration: '3m', target: 1000 }, // stay at 1000 users for 3 minutes
    { duration: '1m', target: 0 },    // ramp-down to 0 users
  ],
  thresholds: {
    http_req_failed: ['rate<0.01'], // error rate should be less than 1%
    http_req_duration: ['p(95)<500'], // 95% of requests should be below 500ms
  },
};

const BASE_URL = 'http://localhost:8080/metaFitAi/users';

export default function () {
  // Simulating getting user profiles
  // We use a small range of IDs to utilize the Redis cache effectively during stability test
  const userId = Math.floor(Math.random() * 100) + 1;
  const res = http.get(`${BASE_URL}/${userId}`);

  check(res, {
    'status is 200': (r) => r.status === 200,
    'status is 429': (r) => r.status === 429, // Expected if rate limits are reached
  });

  sleep(1);
}
