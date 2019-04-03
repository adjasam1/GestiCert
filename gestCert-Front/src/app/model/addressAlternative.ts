import {Certificate} from './certificate';

export class AddressAlternative {

  constructor(public idAddressAlternative: number,
              public linkAddressAlternative: string,
              public certificate: Certificate) {}
}
