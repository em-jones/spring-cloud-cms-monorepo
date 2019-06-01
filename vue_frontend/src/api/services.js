import { cmsClient } from '@/api/index';
import { Auth } from '@/utils/AuthService';

const imageHeader = {
  headers: {
    'Content-Type': 'multipart/form-data',
  },
};

export default {
  cloudinary: {
    async upload(file) {
      const accessToken = await Auth.accessToken();
      imageHeader.headers.Authorization = `Bearer ${accessToken}`;
      const formData = new FormData();
      formData.append('file', file);
      return cmsClient.post('/post/photo', formData, imageHeader);
    },
  },
};
