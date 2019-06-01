import axios from 'axios';

const AUTH_SERVER = 'https://auth.agiantagravic.com';
// const AUTH_SERVER = 'http://localhost:8888';
const data = new FormData();
data.append('client_id', 'cms');
data.append('client_secret', 'secret');
export default {
  async login(username, password) {
    data.append('username', username);
    data.append('password', password);
    data.append('grant_type', 'password');
    const response = await axios.post(`${AUTH_SERVER}/oauth/token`, data, {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    });
    return response;
  },
  async refresh(refreshToken) {
    data.append('refresh_token', refreshToken);
    data.append('grant_type', 'refresh_token');
    const response = await axios.post(`${AUTH_SERVER}/oauth/token`, data, {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    });
    return response;
  },
};
