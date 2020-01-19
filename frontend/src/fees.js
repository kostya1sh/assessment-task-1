import StringUtils from './string_utils';

class InvalidInputExcepiton {
  constructor (message) {
    this.message = message;
  }

  toString () {
    return this.message;
  }
}

export default class Fees {
  constructor (client) {
    this.client = client;
    this._pageSize = 3;

    this.currentPage = 0;
    this.totalPages = 0;

    if (client == null || client.isClient !== true) {
      throw Error('Client is null');
    }

    this.feeList = [];

    this.isLoading = false;
  }

  async firstPage () {
    this.isLoading = true;
    const response = await this.client.getFees(this.currentPage, this._pageSize);
    this.totalPages = response.data['totalPages'];
    this.feeList = response.data['fees'];
    this.currentPage = response.data['page'];

    this.isLoading = false;
  }

  async nextPage () {
    if (this.currentPage + 1 >= this.totalPages) {
      console.log('Last page, can not move to next, page num = ' + this.currentPage);
      return;
    }

    this.isLoading = true;
    const response = await this.client.getFees(this.currentPage + 1, this._pageSize);
    console.log(response.data);

    this.totalPages = response.data['totalPages'];
    this.feeList = response.data['fees'];
    this.currentPage = response.data['page'];

    this.isLoading = false;
  }

  async prevPage () {
    if (this.currentPage <= 0) {
      console.log('First page, can not move to prev, page num = ' + this.currentPage);
      return;
    }

    this.isLoading = true;
    const response = await this.client.getFees(this.currentPage - 1, this._pageSize);
    console.log(response.data);

    this.totalPages = response.data['totalPages'];
    this.feeList = response.data['fees'];
    this.currentPage = response.data['page'];

    this.isLoading = false;
  }

  async reloadPage () {
    this.isLoading = true;
    const response = await this.client.getFees(this.currentPage, this._pageSize);

    if (response.data['fees'].length === 0 && this.currentPage > 0) { // move to prev page if current page is not 0
      await this.prevPage();
    } else {
      this.totalPages = response.data['totalPages'];
      this.feeList = response.data['fees'];
      this.currentPage = response.data['page'];
    }

    this.isLoading = false;
  }

  async add (fromCurrency, toCurrency, fee) {
    if (StringUtils.isEmpty(fromCurrency) || StringUtils.isEmpty(toCurrency) || fee <= 0) {
      throw new InvalidInputExcepiton('Invalid input');
    }

    this.isLoading = true;
    await this.client.addFee(fromCurrency, toCurrency, fee);
    await this.reloadPage();

    this.isLoading = false;
  }

  async remove (id) {
    this.isLoading = true;
    await this.client.removeFee(id);
    await this.reloadPage();

    this.isLoading = false;
  }
}
