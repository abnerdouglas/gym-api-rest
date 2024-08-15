import { ExerciseProvider } from '@/components/ExerciseContent';
import { ChakraProvider } from '@chakra-ui/react';
import { AppProps } from 'next/app';

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <ChakraProvider>
      <ExerciseProvider>
        <Component {...pageProps} />
      </ExerciseProvider>
    </ChakraProvider>
  );
}

export default MyApp;
