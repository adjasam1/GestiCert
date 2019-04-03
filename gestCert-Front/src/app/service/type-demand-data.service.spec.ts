import { TestBed } from '@angular/core/testing';

import { TypeDemandDataService } from './type-demand-data.service';

describe('TypeDemandDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TypeDemandDataService = TestBed.get(TypeDemandDataService);
    expect(service).toBeTruthy();
  });
});
