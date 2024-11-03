describe('BatchProcessing Method Tests', () => {
  let component;

  beforeEach(() => {
    component = render(<MockBatchProcessing />);
  });

  it('should update tenantId state on calling setTenantId', () => {
    const instance = component.container.querySelector('BatchProcessing');
    instance.setTenantId('NewTenant123');
    expect(instance.state.tenantId).toBe('NewTenant123');
  });

  it('should update tabValue state on calling setTabValue', () => {
    const instance = component.container.querySelector('BatchProcessing');
    instance.setTabValue('2');
    expect(instance.state.tabValue).toBe('2');
  });

  it('should update batchId state on calling setBatchId', () => {
    const instance = component.container.querySelector('BatchProcessing');
    instance.setBatchId('Batch123');
    expect(instance.state.batchId).toBe('Batch123');
  });

  it('should call handleChange and update tabValue state', () => {
    const instance = component.container.querySelector('BatchProcessing');
    const event = { preventDefault: jest.fn() }; // Mock event
    instance.handleChange(event, '3');
    expect(instance.state.tabValue).toBe('3');
  });

  it('should update loggingLoader state on calling setLoggingLoader', () => {
    const instance = component.container.querySelector('BatchProcessing');
    instance.setLoggingLoader(true);
    expect(instance.state.loggingLoader).toBe(true);
  });
});
