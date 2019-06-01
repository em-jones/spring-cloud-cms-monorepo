import { Auth } from '@/utils/AuthService';
import axios from 'axios';
import post from './post';
import auth from './auth';
import content from './content';
import services from './services';

const cmsClient = axios.create({
  baseURL: 'https://api.agiantagravic.com',
  // baseURL: 'http://localhost:8000',
});

const loadToken = async () => {
  const token = await Auth.accessToken();
  if (token) {
    // eslint-disable-next-line no-param-reassign
    return {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
  }
  return false;
};

export {
  post,
  content,
  cmsClient,
  auth,
  services,
  loadToken,
};

