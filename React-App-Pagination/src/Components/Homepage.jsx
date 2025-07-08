import { MdDeleteOutline } from "react-icons/md"
import NavBar from "./NavBar"
import { FiEdit2 } from "react-icons/fi"
import { useEffect, useState } from "react"
import axios from "axios"

const Homepage = () => {

    const backendURL = import.meta.env.VITE_BACKEND_BASE_URL;

    const [isLoading, setIsLoading] = useState(true);

    const [fetchedUserData, setFetchedUserData] = useState([]);

    const fetchUserDataFunction = async () => {

        setIsLoading(true);

        try{

            const response = await axios.get(`${backendURL}/users/fetchAllUsers`);

            if ( response.status === 200 ){

                const responseData = response.data;

                console.log(responseData);

                if ( responseData?.length > 0 ){

                    setFetchedUserData(responseData);

                }

                setIsLoading(false);

            }

        }catch(error){

            console.log(error);

            setIsLoading(false);

        }

    }

    useEffect(() => {

        if ( backendURL ){

            fetchUserDataFunction();

        }

    }, []);

    return (

        <>
        
            <NavBar />

            <div className="mx-20 pt-20">

                {!isLoading && fetchedUserData?.length === 0 && (

                    <div className="fixed top-0 left-0 right-0 bottom-0 flex items-center justify-center">No Data found</div>

                )}

                {!isLoading && fetchedUserData?.length > 0 && (

                    <table
                        className="w-full text-left"
                    >

                        <thead
                            className="bg-black text-white h-15"
                        >

                            <tr>

                                <th
                                    className="pl-10"
                                >S.No</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Age</th>
                                <th>Actions</th>

                            </tr>

                        </thead>

                        <tbody>

                            {!isLoading && fetchedUserData?.map((item, index) => (

                                <tr
                                    className="bg-white text-black h-15"
                                    key={item.id}
                                >

                                    <td
                                        className="pl-10"
                                    >{index + 1}</td>
                                    <td>{item.firstName}</td>
                                    <td>{item.lastName}</td>
                                    <td>{item.email}</td>
                                    <td>{item.age}</td>
                                    <td>

                                        <span
                                            className="flex items-center space-x-5"
                                        >

                                            <span>

                                                <FiEdit2 
                                                    className="text-xl transition-all active:scale-80 cursor-pointer"
                                                />

                                            </span>

                                            <span>

                                                <MdDeleteOutline 
                                                    className="text-xl transition-all active:scale-80 cursor-pointer"
                                                />

                                            </span>

                                        </span>

                                    </td>

                                </tr>
                            
                            ))}

                        </tbody>

                    </table>

                )}

            </div>
        
        </>

    )

}

export default Homepage