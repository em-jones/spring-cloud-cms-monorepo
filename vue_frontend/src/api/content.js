/* eslint-disable no-return-await */
import { cmsClient, loadToken } from '@/api';

const base = '/content';

export default {
  async get(id) {
    const headers = await loadToken();
    const endpoint = id ? `/${id}` : '/1';
    if (headers) {
      return await cmsClient.get(`${base}${endpoint}`, headers);
    }
    return await cmsClient.get(`${base}${endpoint}`);
  },
  async list(page) {
    const headers = await loadToken();
    const endpoint = page ? 'list' : 'all';
    if (headers) {
      return await cmsClient.get(`${base}/${endpoint}`, headers);
    }
    return await cmsClient.get(`${base}/${endpoint}`);
  },
  async create(content) {
    const headers = await loadToken();
    return await cmsClient.post(`${base}/save`, content, headers);
  },
  async update(content) {
    const headers = await loadToken();
    return await cmsClient.put(`${base}/save`, content, headers);
  },
};
