import {Certificate} from './certificate';

export class Root {

  constructor(public idRoot?: number,
              public  nameRoot?: string,
              public certificates?: Certificate[]) {}

}
