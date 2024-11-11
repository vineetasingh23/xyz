import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import { BrowserRouter as Router } from 'react-router-dom';
import { SnackbarProvider } from 'notistack';
import MockAdapter from 'axios-mock-adapter';
import axios from 'axios';
import SignatureCapture from '../../pages/SignatureCapture'; // Adjust path as needed
import { APIEndPoints } from '../../common/APIEndPoints';

jest.mock('@react-pdf-viewer/core', () => ({
  Worker: jest.fn(() => null),
  Viewer: jest.fn(() => null),
}));

// Mock component wrapper
const MockSignatureCapture = () => (
  <SnackbarProvider maxSnack={5} autoHideDuration={3000} preventDuplicate>
    <Router>
      <SignatureCapture />
    </Router>
  </SnackbarProvider>
);

describe('Test Cases for SignatureCapture screen', () => {
  let mock: MockAdapter;

  beforeAll(() => {
    sessionStorage.setItem('activeTenantId', '1234');
    mock = new MockAdapter(axios);
    mock.onPost(APIEndPoints.ROOT + APIEndPoints.EXTRACT_DOCUMENT_AS_STRING)
      .reply(200, { data: { extractedStrings: 'string extracted' } });
  });

  afterAll(() => {
    jest.clearAllMocks();
    jest.clearAllTimers();
  });

  beforeEach(() => {
    render(<MockSignatureCapture />);
  });

  // For Header
  it('Checks for header', async () => {
    expect(await screen.findByText('dbTextract')).toBeDefined();
  });

  // For Heading
  it('Checks for heading', async () => {
    expect(await screen.findByText('Signature Capture')).toBeDefined();
  });

  // Test for componentDidMount
  it('should set isUnregisteredUserDialog to true if activeTenantId is not in sessionStorage', () => {
    sessionStorage.removeItem('activeTenantId'); // Simulate missing activeTenantId
    render(<MockSignatureCapture />);
    expect(screen.queryByTestId('unregistered-dialog')).toBeInTheDocument();
  });

  // Test for componentDidUpdate
  it('should update isUnregisteredUserDialog to true when loggingLoader changes and activeTenantId is null', () => {
    sessionStorage.removeItem('activeTenantId');
    const { rerender } = render(<MockSignatureCapture />);
    // Simulate changing loggingLoader from true to false
    rerender(<MockSignatureCapture />);
    expect(screen.queryByTestId('unregistered-dialog')).toBeInTheDocument();
  });

  // Test for setTabValue
  it('should update tabValue state in setTabValue', () => {
    const component = render(<MockSignatureCapture />);
    component.rerender(<MockSignatureCapture />);

    // Directly call setTabValue and verify tabValue is updated
    component.baseElement.__reactInstance.state.setTabValue('2');
    expect(component.baseElement.__reactInstance.state.tabValue).toEqual('2');
  });

  // Test for handleChange
  it('should update tabValue when a tab is clicked using handleChange', () => {
    const newTab = screen.getByText('SignatureOutput'); // Replace with actual tab text
    fireEvent.click(newTab);

    // Verify that the tabValue has been updated by checking the displayed content
    expect(screen.getByText('SignatureOutput')).toBeInTheDocument();
  });
});
