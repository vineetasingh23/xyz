it('calls setTabValue and updates tabValue state correctly when a tab is clicked', () => {
    // Simulate clicking on a tab, assuming the second tab is labeled "Add Members"
    const addMembersTab = screen.getByText('Add Members');
    fireEvent.click(addMembersTab);

    // Check if the expected content for "Add Members" is displayed
    const addMembersContent = screen.getByText('Add or Modify Tenant Details'); // Adjust text as per actual content
    expect(addMembersContent).toBeInTheDocument();
  });

  // Verify state update with setState by checking rendered output for tab 1
  it('calls setState correctly to update tab content on tab click', () => {
    // Click the "Usage" tab to change state
    const usageTab = screen.getByText('Usage');
    fireEvent.click(usageTab);

    // Check if the content for "Usage" is rendered as expected
    const usageContent = screen.getByText('How to Use'); // Adjust text as per actual content
    expect(usageContent).toBeInTheDocument();
  });
});
Explanation of Test Cases
