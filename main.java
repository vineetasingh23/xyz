import React, { createContext, useState } from "react";

// Define the context
const ThemeContext = createContext<any>(null);

// Define the context provider
export const ThemeProvider: React.FC = ({ children }) => {
  const lightTheme = {
    name: "light",
    // Define light theme colors and styles
    // For example:
    background: "#ffffff",
    text: "#000000",
  };

  const darkTheme = {
    name: "dark",
    // Define dark theme colors and styles
    // For example:
    background: "#333333",
    text: "#ffffff",
  };

  const [currentTheme, setCurrentTheme] = useState(lightTheme);

  const toggleTheme = () => {
    setCurrentTheme((prevTheme) =>
      prevTheme === lightTheme ? darkTheme : lightTheme
    );
  };

  return (
    <ThemeContext.Provider value={{ currentTheme, toggleTheme }}>
      {children}
    </ThemeContext.Provider>
  );
};

// Define the context consumer
export const useTheme = () => React.useContext(ThemeContext);
