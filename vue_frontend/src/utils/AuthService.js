/* eslint-disable no-param-reassign,class-methods-use-this */

import moment from 'moment';
import { auth } from '@/api';
import router from './../router';

class Auth {
  constructor() {
    this.login = this.login.bind(this);
  }

  async login({ username, password }) {
    try {
      const { data } = await auth.login(username, password);
      Auth.setSession(data);
      return true;
    } catch (e) {
      return false;
    }
  }

  static async refresh(token) {
    try {
      const { data } = await auth.refresh(token);
      Auth.setSession(data);
      return true;
    } catch (e) {
      // eslint-disable-next-line
      console.warn('Error using refresh token credentialling');
      return false;
    }
  }

  // eslint-disable-next-line camelcase
  static setSession({ access_token, refresh_token, expires_in }) {
    localStorage.setItem('access_token', access_token);
    localStorage.setItem('refresh_token', refresh_token);
    localStorage.setItem('expires_at', moment().add(expires_in, 'seconds').format());
  }

  static logout() {
    // Clear access token and ID token from local storage
    localStorage.removeItem('access_token');
    localStorage.removeItem('id_token');
    localStorage.removeItem('expires_at');
    localStorage.removeItem('refresh_token');
    // navigate to the home route
    router.replace('home');
  }

  static async isAuthenticated() {
    // Check whether the current time is past the
    // access token's expiry time
    const expiresAt = moment(localStorage.getItem('expires_at')).diff(moment(), 'seconds');
    if (expiresAt <= 0) {
      const refreshToken = localStorage.getItem('refresh_token');
      if (refreshToken) {
        // eslint-disable-next-line no-return-await
        return await Auth.refresh(refreshToken);
      }
      return false;
    }
    return true;
  }

  // eslint-disable-next-line consistent-return
  static async accessToken() {
    if (await this.isAuthenticated()) {
      return localStorage.getItem('access_token');
    }
    return false;
  }
}

const AuthService = {
  install(Vue) {
    Vue.prototype.$auth = new Auth();
  },
};

export default AuthService;
export { Auth };
