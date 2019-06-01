/* eslint-disable no-return-await */
import { cmsClient, loadToken } from '@/api';

const base = '/post';

export default {
  async list() {
    const headers = await loadToken();
    if (headers) {
      return await cmsClient.get(`${base}/list`, headers);
    }
    return await cmsClient.get(`${base}/list`);
  },
  async create(post) {
    const headers = await loadToken();
    return await cmsClient.post(`${base}/create`, post, headers);
  },
  async update(post) {
    const headers = await loadToken();
    return await cmsClient.put(`${base}/update`, post, headers);
  },
  async del(id) {
    const headers = await loadToken();
    return await cmsClient.put(`${base}/${id}`, headers);
  },
};
