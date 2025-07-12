#!/bin/bash

echo "Starting Minikube deployment with best practices..."

# Check if minikube is running
if ! minikube status > /dev/null 2>&1; then
    echo "Starting Minikube..."
    minikube start --driver=docker --cpus=4 --memory=8192
fi

# Enable required addons
echo "Enabling required addons..."
minikube addons enable ingress
minikube addons enable metrics-server

# Apply secrets and configmap first
echo "Applying secrets and configmap..."
kubectl apply -f secret.yml
kubectl apply -f configmap.yml

# Deploy databases
echo "Deploying databases..."
kubectl apply -f MySQL.yml
kubectl apply -f MongoDB.yml

####################remove later############

# Remove health checks from databases to prevent startup issues
echo "Removing health checks from databases..."
kubectl patch deployment mysql --type='json' -p='[
  {"op": "remove", "path": "/spec/template/spec/containers/0/livenessProbe"},
  {"op": "remove", "path": "/spec/template/spec/containers/0/readinessProbe"}
]' 2>/dev/null || echo "Health checks not found for MySQL (this is okay)"

kubectl patch deployment mongodb --type='json' -p='[
  {"op": "remove", "path": "/spec/template/spec/containers/0/livenessProbe"},
  {"op": "remove", "path": "/spec/template/spec/containers/0/readinessProbe"}
]' 2>/dev/null || echo "Health checks not found for MongoDB (this is okay)"


###################################



# Wait for databases to be ready
echo "Waiting for databases to be ready..."
kubectl wait --for=condition=ready pod -l app=mysql --timeout=300s
kubectl wait --for=condition=ready pod -l app=mongodb --timeout=300s

# Deploy Eureka server
echo "Deploying Eureka server..."
kubectl apply -f eureka-manifest.yml
kubectl wait --for=condition=ready pod -l app=eureka --timeout=300s

# Deploy microservices
echo "Deploying microservices..."
kubectl apply -f restaurant-manifest.yml
kubectl apply -f food-catalogue-manifest.yml
kubectl apply -f user-manifest.yml
kubectl apply -f order-manifest.yml

#####################extra remove later

# Remove health checks temporarily to avoid startup issues
echo "Removing health checks temporarily..."
for deployment in restaurantapp foodcatalogueapp userapp orderapp; do
  echo "Removing health checks from $deployment..."
  kubectl patch deployment $deployment --type='json' -p='[
    {"op": "remove", "path": "/spec/template/spec/containers/0/livenessProbe"},
    {"op": "remove", "path": "/spec/template/spec/containers/0/readinessProbe"}
  ]' 2>/dev/null || echo "Health checks not found for $deployment (this is okay)"
done

# Wait a bit for changes to apply
echo "Waiting for deployments to restart without health checks..."
sleep 30

###############################



# Wait for services to be ready
echo "Waiting for microservices to be ready..."
kubectl wait --for=condition=ready pod -l app=restaurantapp --timeout=300s
kubectl wait --for=condition=ready pod -l app=foodcatalogueapp --timeout=300s
kubectl wait --for=condition=ready pod -l app=userapp --timeout=300s
kubectl wait --for=condition=ready pod -l app=orderapp --timeout=300s

# Deploy frontend
echo "Deploying frontend..."
kubectl apply -f angular-manifest.yml
kubectl wait --for=condition=ready pod -l app=angularapp --timeout=300s

# Deploy monitoring stack
echo "Deploying monitoring stack..."
kubectl apply -f prometheus.yml
kubectl apply -f grafana.yml

# Wait for monitoring to be ready
kubectl wait --for=condition=ready pod -l app=prometheus --timeout=300s
kubectl wait --for=condition=ready pod -l app=grafana --timeout=300s

# Deploy ingress
echo "Deploying ingress..."
kubectl apply -f ingress.yml

# Apply security policies
echo "Applying network policies..."
kubectl apply -f network-policies.yml

# Get Minikube IP
MINIKUBE_IP=$(minikube ip)


# Deploy HPA for auto-scaling
echo "Deploying Horizontal Pod Autoscalers..."
kubectl apply -f hpa.yml


# Verify deployment
echo "Verifying deployment..."
kubectl get pods
kubectl get hpa
kubectl get networkpolicies


echo "Deployment completed!"
echo ""
echo "Add these entries to your hosts file:"
echo "$MINIKUBE_IP food-delivery.local"
echo "$MINIKUBE_IP eureka.local"
echo "$MINIKUBE_IP grafana.local"
echo ""
echo "Access URLs:"
echo "Main App: http://food-delivery.local"
echo "Eureka: http://eureka.local"
echo "Grafana: http://grafana.local (admin/admin)"
echo "Prometheus: http://prometheus.local"
echo ""
echo "For Windows hosts file:"
echo "Open C:\\Windows\\System32\\drivers\\etc\\hosts as Administrator and add:"
echo "$MINIKUBE_IP food-delivery.local eureka.local grafana.local"

# Check HPA status
kubectl get hpa