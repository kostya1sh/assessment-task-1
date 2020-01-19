import axios from 'axios';

export default class Client {
  constructor (baseurl) {
    this.isClient = true;
    this.baseurl = baseurl;
    this.axios = axios.create({
      baseURL: baseurl,
      timeout: 10000,
      headers: { 'Content-Type': 'application/json' }
    });
  }

  async getCurrencies () {
    return this.axios.get(`/currencies`);
  }

  async calculateExchange (fromCurrency, toCurrency, amount) {
    return this.axios.post(`/currencies/calculator`, {
      'amount': amount,
      'fromCurrency': fromCurrency,
      'toCurrency': toCurrency
    });
  }

  async getFees (page, count) {
    return this.axios.get(`/fees?page=${page}&count=${count}`);
  }

  async addFee (fromCurrency, toCurrency, fee) {
    return this.axios.post(`/fees`, {
      'from': fromCurrency,
      'to': toCurrency,
      'percent': fee
    });
  }

  async removeFee (id) {
    return this.axios.delete(`/fees/${id}`);
  }
}
