import EditBenchmarkForm from '../../components/EditBenchmarkForm';

export default async function EditPage({ params }) {
    const resolvedParams = await params;
    const id = resolvedParams.id;
    
    return <EditBenchmarkForm id={id} />;
}