import { TestBed } from '@angular/core/testing';

import { AddressAlternativeDataService } from './address-alternative-data.service';

describe('AddressAlternativeDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddressAlternativeDataService = TestBed.get(AddressAlternativeDataService);
    expect(service).toBeTruthy();
  });
});
