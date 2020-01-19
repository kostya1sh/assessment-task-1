import StringUtils from './string_utils';

export default class Calculator {
  constructor (client) {
    this.client = client;

    if (client == null || client.isClient !== true) {
      throw Error('Client is null');
    }

    this.from = '';
    this.to = '';
    this.amount = 0.0;
    this.resultAmount = 0.0;
    this.resultCurrency = '';
    this.error = '';
  }

  async onPropertiesChanged (event) {
    console.log(event);
    if (!StringUtils.isEmpty(this.from) && !StringUtils.isEmpty(this.to) && this.amount > 0) {
      const response = await this.client.calculateExchange(this.from, this.to, this.amount);
      this.resultAmount = response.data['amount'];
      this.resultCurrency = response.data['currency'];
    }
  }
}
