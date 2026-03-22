import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  scenarios: {
    // Phase 1: Simulate "Before" (Cold Cache)
    cold_start: {
      executor: 'per-vu-iterations',
      vus: 1,
      iterations: 5, // Just a few requests to see the high latency
      maxDuration: '30s',
    },
    // Phase 2: Simulate "After" (Warm Cache)
    steady_state: {
      executor: 'constant-vus',
      vus: 50,
      duration: '1m',
      startTime: '31s', // Starts after Phase 1
    },
  },
  thresholds: {
    'http_req_duration{scenario:cold_start}': ['avg >= 150'], // Base DB latency expectation
    'http_req_duration{scenario:steady_state}': ['avg <= 80'], // Cached latency expectation
  },
};

const BASE_URL = 'http://localhost:8080/metaFitAi/users';

export default function () {
  const ids = ['user1', 'user2', 'user3', 'user4', 'user5'];
  const userId = ids[Math.floor(Math.random() * ids.length)];
  
  const res = http.get(`${BASE_URL}/${userId}`);
  
  check(res, {
    'is status 200': (r) => r.status === 200,
  });
  
  sleep(0.1);
}
