import React from 'react'
import { render, screen, fireEvent } from '@testing-library/react'
import '@testing-library/jest-dom/extend-expect'
import { withSnackBarHook } from '../path/to/withSnackBarHook'
import CreateStreams from '../path/to/CreateStreams'
import APIConfig from '../path/to/APIConfig'
import ErrorConstants from '../path/to/ErrorConstants'
import SuccessConstants from '../path/to/SuccessConstants'

// Wrap component with Snackbar hook
const MockCreateStreams = withSnackBarHook(CreateStreams)

describe('CreateStreams Component', () => {
  
  beforeEach(() => {
    jest.clearAllMocks()
  })

  test('renders CreateStreams component correctly', () => {
    render(<MockCreateStreams />)
    expect(screen.getByText('Signature Capture')).toBeInTheDocument()
  })

  test('validates empty tenantId and streamName in validationOfParams', () => {
    const { container } = render(<MockCreateStreams />)
    const instance = container.firstChild as any
    
    instance.setState({ tenantId: '', streamName: '' })
    const isValid = instance.validationOfParams()

    expect(isValid).toBe(false)
    expect(screen.getByText(ErrorConstants.EMPTY_FIELDS)).toBeInTheDocument()
  })

  test('showMsg displays success message correctly', () => {
    render(<MockCreateStreams />)
    
    const instance = screen.getByTestId('component-instance') // Assume there’s a way to access the component instance
    instance.showMsg(SuccessConstants.STREAMS_REQUEST_SUCCESSFUL, 'success')
    
    expect(screen.getByText(SuccessConstants.STREAMS_REQUEST_SUCCESSFUL)).toBeInTheDocument()
  })

  test('fetchStream calls API and updates state on success', async () => {
    const mockFetchStreamResponse = { workflowName: 'Test Stream', tenantId: '1234', otherData: {} }
    
    jest.spyOn(APIConfig, 'getCall').mockResolvedValue({ data: mockFetchStreamResponse })
    
    render(<MockCreateStreams />)
    
    const instance = screen.getByTestId('component-instance') // Update based on actual structure
    
    await instance.fetchStream('test-id')
    
    expect(instance.state.streamName).toBe('Test Stream')
    expect(instance.state.streamTenantId).toBe('1234')
  })

  test('saveStreamsForLater displays error on API failure', async () => {
    jest.spyOn(APIConfig, 'postCall').mockRejectedValue({ response: { data: { error: 'Failed to save stream' } } })
    
    render(<MockCreateStreams />)
    const instance = screen.getByTestId('component-instance')
    
    await instance.saveStreamsForLater()
    
    expect(screen.getByText('Failed to save stream')).toBeInTheDocument()
  })

  test('changeToEditMode updates view mode correctly', () => {
    render(<MockCreateStreams />)
    const instance = screen.getByTestId('component-instance')
    
    instance.setState({ tenantId: 'testTenant', currentStreamId: 'testStream', streamTenantId: 'testTenant' })
    instance.changeToEditMode()
    
    expect(instance.state.isViewMode).toBe(false)
  })

  test('validationOfParams fails when tenantId format is invalid', () => {
    render(<MockCreateStreams />)
    const instance = screen.getByTestId('component-instance')
    
    instance.setState({ tenantId: 'invalid-id', streamName: 'StreamName' })
    
    const isValid = instance.validationOfParams()
    expect(isValid).toBe(false)
    expect(screen.getByText(ErrorConstants.ENTER_TENANT_ID)).toBeInTheDocument()
  })
})




import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import MyComponent from './MyComponent'; // Assuming this is your component's file

describe('MyComponent', () => {
  // Mock the theme and color properties as needed
  const mockTheme = { palette: { mode: 'light' } };
  const mockColors = {
    dbPinkBlueGradient: 'pink-blue-gradient',
    dbGreenBlueGradient: 'green-blue-gradient',
    dbBluePinkGradient: 'blue-pink-gradient',
    dbBlueGreenGradient: 'blue-green-gradient',
  };

  // Mock functions to pass as props
  const mockFunctions = {
    handleStreamId: jest.fn(),
    handleTenantId: jest.fn(),
    handleStreamName: jest.fn(),
    handleCurrentStreamId: jest.fn(),
    newStreamCanvas: jest.fn(),
    handleReset: jest.fn(),
    saveStreamsForLater: jest.fn(),
    submitNewStream: jest.fn(),
    changeToEditMode: jest.fn(),
  };

  beforeEach(() => {
    render(<MyComponent theme={mockTheme} Colors={mockColors} {...mockFunctions} />);
  });

  test('renders component and UI elements', () => {
    expect(screen.getByTestId('Create Stream Header')).toBeInTheDocument();
    expect(screen.getByTestId('tenantID')).toBeInTheDocument();
    expect(screen.getByTestId('streamName')).toBeInTheDocument();
    expect(screen.getByTestId('createStreamCanvas')).toBeInTheDocument();
  });

  test('displays correct header based on view mode', () => {
    const header = screen.getByTestId('Create Stream Header');
    expect(header).toHaveTextContent('Create Stream');

    // Toggle to view mode and check header
    fireEvent.click(screen.getByTestId('editButton'));
    expect(header).toHaveTextContent('View Stream');
  });

  test('clicking edit button switches to edit mode', () => {
    fireEvent.click(screen.getByTestId('editButton'));
    expect(mockFunctions.changeToEditMode).toHaveBeenCalledTimes(1);
  });

  test('clicking "Create New Stream" calls newStreamCanvas', () => {
    fireEvent.click(screen.getByText('Create New Stream'));
    expect(mockFunctions.newStreamCanvas).toHaveBeenCalledTimes(1);
  });

  test('clicking save calls saveStreamsForLater', () => {
    fireEvent.click(screen.getByTestId('saveForLater'));
    expect(mockFunctions.saveStreamsForLater).toHaveBeenCalledTimes(1);
  });

  test('clicking reset button opens confirmation dialog', () => {
    fireEvent.click(screen.getByTestId('resetButton'));
    expect(screen.getByText('Reset Confirmation')).toBeInTheDocument();
  });

  test('confirmation dialog confirms reset on button click', () => {
    fireEvent.click(screen.getByTestId('resetButton'));
    const confirmButton = screen.getByText('Confirm');
    fireEvent.click(confirmButton);
    expect(mockFunctions.handleReset).toHaveBeenCalledTimes(1);
  });

  test('displays the correct input values and updates on change', () => {
    const tenantIdInput = screen.getByTestId('tenantID');
    const streamNameInput = screen.getByTestId('streamName');

    fireEvent.change(tenantIdInput, { target: { value: '123' } });
    expect(mockFunctions.handleTenantId).toHaveBeenCalledWith('123');

    fireEvent.change(streamNameInput, { target: { value: 'New Stream' } });
    expect(mockFunctions.handleStreamName).toHaveBeenCalledWith('New Stream');
  });

  test('clicking close button in update dialog closes the dialog', () => {
    fireEvent.click(screen.getByTestId('closeButton'));
    expect(screen.queryByText('Stream ID')).not.toBeInTheDocument();
  });

  test('snapshot of initial render', () => {
    const { asFragment } = render(<MyComponent theme={mockTheme} Colors={mockColors} {...mockFunctions} />);
    expect(asFragment()).toMatchSnapshot();
  });
});
