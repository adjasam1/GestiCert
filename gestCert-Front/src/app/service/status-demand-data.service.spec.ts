import { TestBed } from '@angular/core/testing';

import { StatusDemandDataService } from './status-demand-data.service';

describe('StatusDemandDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StatusDemandDataService = TestBed.get(StatusDemandDataService);
    expect(service).toBeTruthy();
  });
});
