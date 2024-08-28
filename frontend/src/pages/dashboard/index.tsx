import { useRouter } from 'next/router';
import { Box, Flex, Button, Text, Spinner } from '@chakra-ui/react';
import Cookies from 'js-cookie';
import useAuth from '@/hooks/useAuth';
import CardTemplate from '@/components/CardTemplate';
import Parameters from '@/components/parameters/Parameters';
import Head from 'next/head';

const Dashboard = () => {
  const { isAuthenticated } = useAuth();
  const router = useRouter();

  const handleLogout = () => {
    Cookies.remove('token');
    router.push('/login');
  };

  if (!isAuthenticated) {
    return (
      <Flex justify="center" align="center" height="100vh">
        <Spinner size="xl" />
      </Flex>
    );
  }

  return (
    <Box minH="100vh" bg="gray.100">
      <Flex
        as="nav"
        bg="blue.500"
        color="white"
        padding="1rem"
        justifyContent="space-between"
        alignItems="center"
        mb={8}
      >
        <Head>
          <title>Dashboard</title>
        </Head>

        <Text fontSize="xl" fontWeight="bold">Dashboard</Text>
        <Button onClick={handleLogout} colorScheme="red">
          Logout
        </Button>
      </Flex>
      <Box p={4}>
        <Text fontSize="2xl" fontWeight="bold">Exercises Dashboard</Text>
        <Text>Select the parameter and search for the corresponding exercise data.</Text>
      </Box>
      <Parameters/>
      <CardTemplate/>
    </Box>
    
  );
};

export default Dashboard;
