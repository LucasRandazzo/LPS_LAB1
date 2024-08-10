import axios from 'axios';

import { API_URL } from '../helpers/api';

class BaseRepository {
  constructor({ baseUrl = API_URL, route = '' }) {
    this.url = { baseUrl } + { route };
  }

  async get() {
    axios.get(this.url);
  }

  async post(data) {
    axios.post(this.url, data);
  }

  async put(data) {
    axios.put(this.url, data);
  }

  async delete(id) {
    axios.delete(this.url + id);
  }
}

export default BaseRepository;
